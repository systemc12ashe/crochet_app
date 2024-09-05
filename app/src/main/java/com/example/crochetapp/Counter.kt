package com.example.crochetapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController

class Counter {

    fun count(operation: Boolean, clickCount: Int): TextFieldValue {
        var counter = clickCount;
        if (operation){
            counter++
        } else{
            if ((counter - 1) < 0){
                counter == 0
            } else {
                counter --
            }

        }
        return TextFieldValue(counter.toString())
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun counter(name: String, count:Int = 0) {
        var clickCount = remember { mutableStateOf(TextFieldValue("0")) }
        var value = clickCount.value.text

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("$name Counter", fontSize = 20.sp, modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 40.dp, 0.dp, 40.dp), textAlign = TextAlign.Center)
            Text("$value", fontSize = 30.sp, modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 40.dp), textAlign = TextAlign.Center)

            val pattern = remember { Regex("^\\d+\$") }
            TextField(
                value = clickCount.value,
                onValueChange = {
                    if (it.text.isDigitsOnly()) clickCount.value = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(0.dp,0.dp,0.dp,20.dp)
            )

            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly){
                FilledTonalButton(
                    onClick = { clickCount.value = count(true, clickCount.value.text.toInt()) },
                    contentPadding = PaddingValues(0.dp),
                    modifier= Modifier.size(40.dp))
                {
                    Icon(Icons.Filled.Add, "Increase $name")
                }

                FilledTonalButton(
                    onClick = { clickCount.value = TextFieldValue("0") },
                    contentPadding = PaddingValues(0.dp),
                    modifier= Modifier.size(40.dp))
                {
                    Icon(Icons.Filled.Refresh, "Reset $name")
                }

                FilledTonalButton(
                    onClick = { clickCount.value = count(false, clickCount.value.text.toInt()) },
                    contentPadding = PaddingValues(0.dp),
                    modifier= Modifier.size(40.dp))
                {
                    Icon(Icons.Filled.Remove, "Decrease $name")
                }
            }
        }

    }
    @Composable
    fun CounterPage(navController: NavController){
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            counter("Row")
            counter("Stitch")
            BottomNavigationBar(navController = navController, modifier = Modifier)
        }
    }
}