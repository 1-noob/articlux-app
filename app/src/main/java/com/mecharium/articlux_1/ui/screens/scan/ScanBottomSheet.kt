package com.mecharium.articlux_1.ui.screens.scan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.mecharium.articlux_1.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanBottomSheet(
    onDismiss: () -> Unit
) {

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    var message by remember { mutableStateOf("Scan New Articles") }
    var loading by remember { mutableStateOf(false) }
    var scanFinished by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        scope.launch {
            try {
                loading = true
                val response = RetrofitInstance.api.scan()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        message = "${it.stats.new_articles} new articles found.\n${it.stats.classified} are classified.\n${it.stats.needs_review} needs your review.\n"
                        scanFinished = true
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

            if (scanFinished && !loading) {
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
            }
        }
    }

}