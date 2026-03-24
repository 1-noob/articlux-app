package com.mecharium.articlux_1.ui.screens.scan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.mecharium.articlux_1.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

// UI state
enum class ScanStage {
    SCANNING,
    INSERT_READY,
    INSERTING,
    REVIEW_READY,
    FINISHED
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanBottomSheet(
    onDismiss: () -> Unit,
    onStartReview: () -> Unit
) {

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    var message by remember { mutableStateOf("Scan New Articles") }
    var loading by remember { mutableStateOf(false) }

    var stage by remember { mutableStateOf(ScanStage.SCANNING) }

    var classifiedCount by remember { mutableStateOf(0) }
    var reviewCount by remember { mutableStateOf(0) }



    LaunchedEffect(Unit) {
        scope.launch {
            try {
                loading = true
                val response = RetrofitInstance.api.scan()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {

                        classifiedCount = it.stats.classified
                        reviewCount = it.stats.needs_review

                        if (it.stats.new_articles == 0){
                            message = "No new articles found."
                            stage = ScanStage.FINISHED
                        } else {
                            message =
                                "${it.stats.new_articles} new articles found.\n${it.stats.classified} are classified.\n${it.stats.needs_review} needs your review.\n"
                            if (classifiedCount > 0) {
                                stage = ScanStage.INSERT_READY
                            } else {
                                stage = ScanStage.REVIEW_READY
                            }
                        }
                    }
                } else {
                    message = "Scan failed: ${response.code()}"
                }
            } catch (e: Exception) {
                message = " Error: ${e.message}"
            } finally {
                loading = false
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (loading) {
                CircularProgressIndicator()
                Spacer(Modifier.height(16.dp))
            }

            Text(message)

            Spacer(Modifier.height(24.dp))


            // PROCEED button - inserts articles into database
            if ( stage == ScanStage.INSERT_READY && !loading ) {
                Button(
                    onClick = {
                        scope.launch {
                            try {
                                loading = true
                                message = "Inserting articles..."

                                val response = RetrofitInstance.api.proceed()

                                if (response.isSuccessful) {
                                    val body = response.body()
                                    message = body?.message ?: "Insert completed."
                                    stage = ScanStage.REVIEW_READY
                                } else {
                                    message = "Insert failed: ${response.code()}"
                                }
                            } catch (e: Exception) {
                                message = "Insert error: ${e.message}"
                            } finally {
                                loading = false
                            }
                        }
                    }
                ) {
                    Text("PROCEED!")
                }

                Spacer(Modifier.height(16.dp))
            }

            // PROCEED btn - proceed to review the articles
            if (stage == ScanStage.REVIEW_READY && !loading) {
                Button(
                    onClick = {
                        // Starting review process
                        onDismiss()
                        onStartReview()
                    }
                ) {
                    Text("PROCEED!")
                }

                Spacer(Modifier.height(16.dp))
            }

            if (!loading && stage == ScanStage.FINISHED){
                Button(
                    onClick = {onDismiss()}
                ) {
                    Text("CLOSE")
                }
            }

            Spacer(Modifier.height(16.dp))
        }
    }

}