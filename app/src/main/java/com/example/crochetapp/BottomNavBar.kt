package com.example.crochetapp

import androidx.compose.foundation.layout.Box
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Counter : BottomNavItem("counter", Icons.Default.Timer, "Counter")
    object AddYarn : BottomNavItem("addYarn", Icons.Default.AddCircle, "Add Yarn")
    object YarnList : BottomNavItem("yarnList", Icons.Default.Inventory2, "View Yarn")
}

@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier) {
    BottomNavigation(backgroundColor = MaterialTheme.colorScheme.primary, modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        BottomNavigationItem(
            selected = currentRoute == BottomNavItem.Counter.route,
            onClick = {
                navController.navigate(BottomNavItem.Counter.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            icon = { Icon(BottomNavItem.Counter.icon, contentDescription = null) },
            label = { Text(BottomNavItem.Counter.label) }
        )
        BottomNavigationItem(
            selected = currentRoute == BottomNavItem.AddYarn.route,
            onClick = {
                navController.navigate(BottomNavItem.AddYarn.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            icon = { Icon(BottomNavItem.AddYarn.icon, contentDescription = null) },
            label = { Text(BottomNavItem.AddYarn.label) }
        )
        BottomNavigationItem(
            selected = currentRoute == BottomNavItem.YarnList.route,
            onClick = {
                navController.navigate(BottomNavItem.YarnList.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            icon = { Icon(BottomNavItem.YarnList.icon, contentDescription = null) },
            label = { Text(BottomNavItem.YarnList.label) }
        )
    }
}