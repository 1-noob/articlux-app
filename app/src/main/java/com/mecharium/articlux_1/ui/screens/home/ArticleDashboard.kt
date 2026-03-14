package com.mecharium.articlux_1.ui.screens.home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel

import com.mecharium.articlux_1.ui.components.ArticleCard

@Composable
fun ArticleDashboard(
    viewModel: HomeViewModel = viewModel()
) {

    val articles by viewModel.articles
    val currentPage by viewModel.currentPage

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    val columns = if (screenWidthDp < 600) 1 else 4

    LaunchedEffect(Unit) {
        viewModel.load_Articles(1)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { viewModel.previousPage() },
                enabled = currentPage > 1
            ) {
                Text("Previous")
            }

            Button(
                onClick = { viewModel.nextPage() }
            ) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.weight(1f)
        ) {
            items(articles) { article ->
                ArticleCard(
                    article = article,
                    modifier = Modifier.padding(8.dp),
                    onClick = {}
                )
            }
        }
    }
}