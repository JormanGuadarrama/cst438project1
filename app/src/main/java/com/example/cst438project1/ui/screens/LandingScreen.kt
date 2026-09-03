package com.example.cst438project1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cst438project1.ui.theme.CST438Project1Theme

@Composable
fun LandingScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome!",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            onClick = {

            }
        ) {
            Text("Login")
        }

        Button(
            onClick = {

            }
        ) {
            Text("Sign Up")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    CST438Project1Theme {
        LandingScreen()
    }
}