package com.mecharium.articlux_1.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mecharium.articlux_1.R

import com.mecharium.articlux_1.data.model.Article
import com.mecharium.articlux_1.ui.components.StarRating
@Composable
fun ArticleDetailsDialog(
    article: Article,
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
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

                StarRating(article.rating ?: 0)

                Spacer(modifier = Modifier.height(8.dp))

                Text("Category: ${article.category}")

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    FloatingActionButton (
                        onClick = {},
                        modifier = Modifier.size(48.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.notebooklm_logo),
                            contentDescription = "Open NotebookLM",
                            modifier = Modifier.size(24.dp)
                        )
                    }

                }
            }
        }
    }
}