package com.mecharium.articlux_1.ui.screens.home


import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mecharium.articlux_1.data.model.Article
import com.mecharium.articlux_1.ui.components.ArticleCard




@Composable
fun HomeScreen(){
    val sampleArticle = Article(
        title = "New News Article",
        status = "pending",
        rating = 3,
        category = "test"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ArticleCard(
                article = sampleArticle,
                onClick = {
                    // do nothing
                }
            )
        }
    }
}
