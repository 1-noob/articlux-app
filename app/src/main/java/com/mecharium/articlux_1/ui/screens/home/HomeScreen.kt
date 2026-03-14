package com.mecharium.articlux_1.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.VerticalDivider

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

import com.mecharium.articlux_1.ui.theme.TomorrowFont
import com.mecharium.articlux_1.ui.screens.scan.ScanBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    // Variables
    var selectedItem by remember { mutableIntStateOf(0) }
    var showScan by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Articlux",
                            modifier = Modifier.padding(start = 40.dp),
                            fontFamily = TomorrowFont,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 26.sp,
                            color = Color.Gray
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Scan new Articles
                    showScan = true
                },
                shape = RoundedCornerShape(50),
                containerColor = MaterialTheme.colorScheme.primary,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 8.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Scan Articles"
                )
            }
        }
    ) { paddingValues ->

        Row(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(8.dp)
        ) {
            NavigationRail(
                modifier = Modifier.padding(horizontal = 10.dp).width(60.dp)
            ) {

                // Home - Returns to the dashboard screen
                NavigationRailItem(
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home, contentDescription = "Home"
                        )
                    },
                    colors = NavigationRailItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.clip(RoundedCornerShape(16.dp))
                                       .background(
                                           if (selectedItem == 0)
                                               MaterialTheme.colorScheme.secondaryContainer
                                           else
                                                Color.Transparent
                                       )
                        .padding(4.dp)
                    // label = {Text("Home")}
                )

                // Scan - Scans for new Articles and inserts them into the database
                NavigationRailItem(
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1},
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Scan"
                        )
                    },
                    colors = NavigationRailItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.clip(RoundedCornerShape(16.dp))
                                       .background(
                                           if (selectedItem == 1)
                                               MaterialTheme.colorScheme.secondaryContainer
                                           else
                                               Color.Transparent
                                       )
                                       .padding(4.dp)
                    // label = {Text("Scan")}
                )

            }

            // Divider
            VerticalDivider(
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Main Content Area
            Box(
                modifier = Modifier.fillMaxSize().padding(24.dp)
            ) {
                Text("Main Content Area")
            }
        }
    }
    if (showScan){
        ScanBottomSheet (
            onDismiss = { showScan = false }
        )
    }

}