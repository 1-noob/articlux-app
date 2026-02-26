package com.mecharium.articlux_1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.modifier.modifierLocalConsumer
import com.mecharium.articlux_1.ui.components.PrimaryButton
import kotlinx.coroutines.launch
import com.mecharium.articlux_1.data.remote.RetrofitInstance
import com.mecharium.articlux_1.data.model.ReviewArticle
import com.mecharium.articlux_1.ui.review.HomeMode
import com.mecharium.articlux_1.ui.review.ReviewState
import kotlinx.coroutines.CoroutineScope
import okhttp3.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var scanMessage by remember { mutableStateOf("") }
    var isScanSuccessful by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    var homeMode by remember { mutableStateOf<HomeMode>(HomeMode.Idle) }
    val snackbarHostState = remember { SnackbarHostState() }

    suspend fun startReview(
        scope: CoroutineScope,
        snackbarHostState: SnackbarHostState,
        onModeChange: (HomeMode) -> Unit
    ) {
        try {
            val response = RetrofitInstance.api.review(
                action = "get_next",
                url = null,
                category = null
            )

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    when (body.state) {
                        "ready" -> {
                            val articleData = body.data

                            if (articleData != null) {
                                val article = ReviewArticle(
                                    url = articleData.url,
                                    title = articleData.title
                                )

                                onModeChange(
                                    HomeMode.Review(
                                        ReviewState.ShowingArticle(article)
                                    )
                                )
                            }
                        }

                        "empty" -> {
                            snackbarHostState.showSnackbar(
                                message = "Review Process Complete!"
                            )
                            onModeChange(HomeMode.Idle)
                        }

                        else -> {
                            snackbarHostState.showSnackbar(
                                message = "Unexpected State"
                            )
                            onModeChange(HomeMode.Idle)
                        }
                    }
                }
            } else {
                snackbarHostState.showSnackbar(
                    message = "Error :${response.code()}"
                )
            }
        } catch (e: Exception) {
            snackbarHostState.showSnackbar(
                message = "Failed: ${e.message}"
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (homeMode is HomeMode.Idle) {
                PrimaryButton(
                    text = "SCAN",
                    onClick = {

                        showBottomSheet = true

                        // Test API Connection
                        scope.launch {
                            try {
                                isLoading = true

                                val response = RetrofitInstance.api.scan()

                                if (response.isSuccessful) {

                                    val body = response.body()

                                    body?.let {

                                        val classified = it.stats.classified
                                        val review = it.stats.needs_review
                                        val newArticles = it.stats.new_articles

                                        scanMessage =
                                            "Found $newArticles articles :\n" +
                                                    "$classified are classified and $review need your reviewing.\n"

                                        // Mark scan successful
                                        isScanSuccessful = true

                                    }
                                } else {
                                    scanMessage = "Error: ${response.code()}"
                                    showBottomSheet = true
                                    isScanSuccessful = false
                                }
                            } catch (e: Exception) {
                                scanMessage = "Failed: ${e.message}"
                                showBottomSheet = true
                                isScanSuccessful = false
                            } finally {
                                isLoading = false
                            }
                        }
                    },
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (isLoading) "Scanning..." else scanMessage
                    )

                    Spacer(Modifier.height(40.dp))

                    PrimaryButton(
                        // Change to Proceed (Synchronising articles into DB)
                        text = if (isScanSuccessful) "Proceed" else "Close",
                        onClick = {
                            if (isScanSuccessful) {
                                scope.launch {
                                    try {
                                        isLoading = true

                                        val response = RetrofitInstance.api.proceed()

                                        if (response.isSuccessful) {
                                            val body = response.body()
                                            body?.let {
                                                val inserted = it.stats.inserted
                                                // val skipped = it.stats.skipped
                                                val total = it.stats.total

                                                scanMessage =
                                                    "Synchronisation Complete:\n" +
                                                            "Inserted $inserted  articles.\n" +
                                                            "Total articles : $total"

                                                // Reset state
                                                isScanSuccessful = false


                                                startReview(
                                                    scope = scope,
                                                    snackbarHostState = snackbarHostState,
                                                    onModeChange = { mode -> homeMode = mode }
                                                )

                                            }
                                        } else {
                                            scanMessage = "Error in Proceeding : ${response.code()}"
                                        }
                                    } catch (e: Exception) {
                                        scanMessage = "Proceed Failed: ${e.message}"
                                    } finally {
                                        isLoading = false
                                    }
                                }
                            } else {
                                // Normal close behaviour
                                scope.launch {
                                    sheetState.hide()
                                }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .width(180.dp)
                    )
                }
            }
        }
    }
}