package com.mecharium.articlux_1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import com.mecharium.articlux_1.ui.components.PrimaryButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        PrimaryButton(
            text = "SCAN",
            onClick = {
                // Scan the feed for new articles
                showBottomSheet = true
            },
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text("Button Pressed!")

                Spacer(Modifier.height(16.dp))

                PrimaryButton(
                    // Change to Proceed (Synchronising articles into DB)
                    text = "Close",
                    onClick = { showBottomSheet = false }
                )

            }
        }
    }

}