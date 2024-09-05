package com.example.crochetapp

import android.os.Environment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.io.File

val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
var file = File(path, "yarn.csv")


class Yarn (){

    private fun writeYarn(name: String = "", color: String = "", material: String = "", weight: String = "", length: String = ""){
        if (!file.exists()) {
            file.appendText("Name, Color, Material, Weight, Length")
        }
        file.appendText("\n$name, $color, $material, $weight, $length")
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddYarnPage(navController: NavController){
        Column(verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
            var name by remember { mutableStateOf("") }
            var color by remember { mutableStateOf("") }
            var material by remember { mutableStateOf("") }
            var weight by remember { mutableStateOf("") }
            var length by remember { mutableStateOf("") }

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.padding(0.dp, 40.dp, 0.dp, 0.dp)
            )

            TextField(
                value = color,
                onValueChange = { color = it },
                label = { Text("Color") }
            )

            TextField(
                value = material,
                onValueChange = { material = it },
                label = { Text("Material") }
            )

            TextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Weight") }
            )

            TextField(
                value = length,
                onValueChange = { length = it },
                label = { Text("Length") }
            )

            Button(onClick = { writeYarn(name, color, material, weight, length); name = ""; color = ""; material = ""; weight = ""; length = ""}) {
                Text("Add")
            }
            BottomNavigationBar(navController = navController, modifier = Modifier)
        }
    }
}