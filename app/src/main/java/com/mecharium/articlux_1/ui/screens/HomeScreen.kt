package com.mecharium.articlux_1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import com.mecharium.articlux_1.ui.components.PrimaryButton
import kotlinx.coroutines.launch
import com.mecharium.articlux_1.data.remote.RetrofitInstance
import okhttp3.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var scanMessage by remember { mutableStateOf("") }
    var isScanSuccessful by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
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
                        isLoading= false
                    }
                }
            },
            modifier = Modifier.padding(horizontal = 24.dp)
        )
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
                                                "Inserted $inserted  articles.\n"
                                                "Total articles : $total"

                                            // Reset state
                                            isScanSuccessful = false
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
                                if (!sheetState.isVisible){
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