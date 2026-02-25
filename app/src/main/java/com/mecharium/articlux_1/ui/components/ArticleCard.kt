package com.mecharium.articlux_1.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mecharium.articlux_1.data.model.Article



@Composable
fun ArticleCard(
    article: Article,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

    val backgroundColor =
        if (article.status.trim().equals("completed", ignoreCase = true)) {
            Color(0xFFE8F6C9)             //0xFFAED581
        } else {
            MaterialTheme.colorScheme.surface
    }

    Card(
        modifier = modifier
            .width(300.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Top Row -> Stars
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                repeat(5){ index ->
                    val starColor =
                        if (index < article.rating) Color(0xFFFFC107) else Color.LightGray

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "star",
                        tint = starColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            // Title
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            // Category
            Text(
                text = article.category,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = Color.Gray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    MaterialTheme {
        ArticleCard(
            article = Article(
                title = "Sample News Article Title",
                status = "completed",
                rating = 3,
                category = "Test"
            )
        )
    }
}

