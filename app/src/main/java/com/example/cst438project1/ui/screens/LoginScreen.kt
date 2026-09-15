package com.example.cst438project1.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cst438project1.ui.theme.CST438Project1Theme
import com.example.cst438project1.ui.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavHostController,authViewModel: AuthViewModel){
    var userName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val authError by authViewModel.errorMessage.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text="Login",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 30.dp)
        )
        OutlinedTextField(
            value = userName,
            onValueChange= { text ->
                userName=text
            },
            label = {
                Text("Username")
            },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom=16.dp),
            singleLine = true
        )
        OutlinedTextField(
            value=password,
            onValueChange= { text ->
                password=text
            },
            label = {
                Text("Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 24.dp),
            singleLine = true
        )
        Button(
            onClick = {
                if (userName.isBlank() || password.isBlank()) {
                    authViewModel.setError("Username and password cannot be empty")
                } else {
                    authViewModel.login(userName, password) {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text="Submit", fontSize = 20.sp)
        }
        if (authError.isNotEmpty()){
            Text(
                text=authError,
                color=Color.Red,
                fontSize = 16.sp,
                modifier = Modifier.padding(top=12.dp)
            )
        }
        Text(
            text="Back",
            color = Color.Blue,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top=20.dp)
                .clickable{
                    navController.popBackStack()
                }
        )
    }

}