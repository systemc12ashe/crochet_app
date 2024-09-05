package com.example.crochetapp

import android.os.Environment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.io.File

class YarnList {
    @Composable
    fun getYarnList(yarns: List<String>) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            for (yarn in yarns) {
                val splitString = yarn.split(", ")
                if (splitString[0]!="Name"){
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(){
                                Text(text = "Brand", fontWeight = FontWeight.Bold, modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp))
                                Text(text = splitString[0])
                            }
                            Row(){
                                Text(text = "Color", fontWeight = FontWeight.Bold, modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp))
                                Text(text = splitString[1])
                            }
                            Row(){
                                Text(text = "Material", fontWeight = FontWeight.Bold, modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp))
                                Text(text = splitString[2])
                            }
                            Row(){
                                Text(text = "Weight", fontWeight = FontWeight.Bold, modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp))
                                Text(text = splitString[3])
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun YarnListScreen(
        navController: NavController,
        modifier: Modifier = Modifier,
        onScanButtonClicked: () -> Unit = {}
    ) {
        val path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

        val file = File(path, "yarn.csv")
        if (!file.exists()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("To begin viewing yarns, save yarns", textAlign = TextAlign.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp), style = TextStyle(fontSize = 25.sp)
                )
            }
        }
        else {
            val yarns: List<String> = file.useLines { it.toList() }
            Box() {
                getYarnList(yarns)
                BottomNavigationBar(navController = navController, modifier = Modifier.align(Alignment.BottomCenter))
            }
        }


        return
    }
}