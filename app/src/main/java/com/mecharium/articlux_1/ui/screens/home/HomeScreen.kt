package com.mecharium.articlux_1.ui.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.mecharium.articlux_1.data.model.Article
import com.mecharium.articlux_1.ui.components.ArticleCard




@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun HomeScreen(){

    val configuration = LocalConfiguration.current
    val screenWithDp = configuration.screenWidthDp

    val columns = if (screenWithDp <600) {
        1 // for smartphones
    } else {
        4 // for tablets
    }

    // Sample Data
    val articles = listOf(
        Article("Safety gaps in charter planes, New Solid Waste Management Rules, and Discombobulator", "completed", 4, "Android"),
        Article("India-Arab Foreign Ministers’ Meeting, Modernisation of CPI inflation, and New Ramsar sites", "pending", 3, "UI"),
        Article("US armada moves closer to Iran, India-EU ink ‘mother of all deals’, and Xi purges top PLA General", "completed", 5, "Backend"),
        Article("Economic Survey 2025-26: Key takeaways for your UPSC exam", "pending", 2, "Database"),
        Article("What is balance of power?", "completed", 4, "Android"),
        Article("Why India’s rice production surge raises concerns over food security, sustainability", "pending", 1, "Design")
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.fillMaxSize(),
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
    }
}
