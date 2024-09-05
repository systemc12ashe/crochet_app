package com.example.crochetapp

import android.os.Bundle
import androidx.compose.runtime.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.crochetapp.ui.theme.CrochetAppTheme
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrochetAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Page()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Page() {
    val counterPage = Counter();
    val addYarnPage = Yarn();
    val yarnListPage = YarnList();
    val navController = rememberNavController()
    CrochetAppTheme {
        NavHost(navController = navController, startDestination = "counter") {
            composable("counter") {
                counterPage.CounterPage(navController);
            }
            composable("addYarn") {
                addYarnPage.AddYarnPage(navController);
            }
            composable("yarnList") {
                yarnListPage.YarnListScreen(navController);
            }
        }
    }
}