package com.mecharium.articlux_1.ui.screens.review

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendModeColorFilter
import androidx.compose.ui.unit.dp


// UI state enum
enum class ReviewUiState {
    MAIN,
    CONFIRM_DISCARD,
    CONFIRM_SPECIAL,
    SELECT_CATEGORY,
    CONFIRM_CATEGORY
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewBottomSheet(
    onDismiss: () -> Unit,
    viewModel: ReviewViewModel = viewModel()
){
    val sheetState = rememberModalBottomSheetState ()

    val article by viewModel.article
    val loading by viewModel.loading
    val message by viewModel.message

    var uiState by remember { mutableStateOf(ReviewUiState.MAIN) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    var showResultDialog by remember { mutableStateOf(false) }
    var resultMessage by remember { mutableStateOf("") }

    // Automatically load the first Article
    LaunchedEffect(Unit) {
        viewModel.loadNext()
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
            if (loading){
                CircularProgressIndicator()
                Spacer(Modifier.height(16.dp))
            }
            
            // Optional message
            // Spacer

            article?.let { art ->
                Text(art.title)

                Spacer(Modifier.height(16.dp))

                when (uiState) {

                    ReviewUiState.MAIN -> {

                        Button(
                            onClick = { uiState = ReviewUiState.CONFIRM_DISCARD },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("DISCARD")
                        }

                        Button(
                            onClick = { uiState = ReviewUiState.SELECT_CATEGORY },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("CHOOSE A CATEGORY")
                        }

                        Button(
                            onClick = { uiState = ReviewUiState.CONFIRM_SPECIAL },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("SPECIAL CATEGORY")
                        }
                    }

                        ReviewUiState.CONFIRM_DISCARD -> {

                            ConfirmActionScreen(
                                text = "Discard this article?",
                                onConfirm = {
                                    viewModel.discard(art.url)
                                    uiState = ReviewUiState.MAIN
                                },
                                onBack = { uiState = ReviewUiState.MAIN }
                            )

                        }

                        ReviewUiState.CONFIRM_SPECIAL -> {

                            ConfirmActionScreen(
                                text = "Insert article with special category?",
                                onConfirm = {
                                    viewModel.insertSpecial(art.url)
                                    uiState = ReviewUiState.MAIN
                                },
                                onBack = { uiState = ReviewUiState.MAIN }
                            )

                        }

                        ReviewUiState.SELECT_CATEGORY -> {

                            CategorySelectionScreen(
                                onCategorySelected = {
                                    selectedCategory = it
                                    uiState = ReviewUiState.CONFIRM_CATEGORY
                                },
                                onBack = { uiState = ReviewUiState.MAIN }
                            )

                        }

                        ReviewUiState.CONFIRM_CATEGORY -> {

                            ConfirmActionScreen(
                                text = "Insert article with \'$selectedCategory\'?",
                                onConfirm = {
                                    selectedCategory?.let {cat ->
                                        viewModel.insertPrebuilt(art.url, cat)
                                        uiState = ReviewUiState.MAIN}
                                },
                                onBack = { uiState = ReviewUiState.MAIN }
                            )

                        }

                    }
                }
            }

        }

        if (showResultDialog) {
            BasicAlertDialog(
                onDismissRequest = { showResultDialog = false }
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    shape = MaterialTheme.shapes.medium
                ) {

                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Result")

                        Spacer(Modifier.height(16.dp))

                        Text(resultMessage)

                        Spacer(Modifier.height(24.dp))

                        Button(
                            onClick = { showResultDialog = false }
                        ) {
                            Text("OK!")
                        }
                    }

                }
            }
        }
}