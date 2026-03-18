package com.mecharium.articlux_1.ui.screens.review

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelectionScreen(
    onCategorySelected: (String) -> Unit,
    onBack: () -> Unit
) {

    val categories = listOf(
        "Art & Culture",
        "Beyond trending",
        "Current Affairs",
        "Ethics simplified",
        "Upsc issue at a glance",
        "Knowledge nugget",
        "Mains Answer Writing",
        "Upsc key",
        "World this week"
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Choose a category")

        Spacer(Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            TextField(
                value = selectedCategory,
                onValueChange = {},
                readOnly = true,
                label = { Text("Category") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                categories.forEach { category ->

                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            selectedCategory = category
                            expanded = false
                        }
                    )

                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                if (selectedCategory.isNotEmpty()) {
                    onCategorySelected(selectedCategory)
                }
            }
        ) {
            Text("CONFIRM CATEGORY")
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = onBack) {
            Text("GO BACK")
        }
    }
}