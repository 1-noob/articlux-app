package com.mecharium.articlux_1.ui.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mecharium.articlux_1.data.model.Article
import com.mecharium.articlux_1.ui.components.ArticleCard




@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun HomeScreen(viewModel : HomeViewModel = viewModel()){

    val configuration = LocalConfiguration.current
    val screenWithDp = configuration.screenWidthDp

    val columns = if (screenWithDp <600) {
        1 // for smartphones
    } else {
        4 // for tablets
    }

    val articles by viewModel.articles

    LaunchedEffect(Unit) {
        viewModel.loadArticles(1)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) { Text(
                text = "{${articles.size}}",
                modifier = Modifier.padding(16.dp)
            )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columns),
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(articles) { article ->
                        ArticleCard(
                            article = article,
                            modifier = Modifier.padding(8.dp),
                            onClick = {
                                // do nothing
                            }
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { viewModel.previousPage() },
                        enabled = viewModel.currentPage > 1) {
                        Text("Previous")
                    }

                    Button(onClick = { viewModel.nextPage() }) {
                        Text("Next")
                    }
                }
            }
    }
}
