package com.mecharium.articlux_1.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

import com.mecharium.articlux_1.data.model.Article

@Composable
fun ArticleDetailsDialog(
    article: Article,
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss
    ) {

        Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = 8.dp
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close dialog"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = article.title ?: "Untitled",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Rating: ${article.rating}")

                Spacer(modifier = Modifier.height(8.dp))

                Text("Category: ${article.category}")
            }
        }
    }
}