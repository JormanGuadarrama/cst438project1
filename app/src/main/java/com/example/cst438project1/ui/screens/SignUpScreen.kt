package com.example.cst438project1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.cst438project1.ui.theme.CST438Project1Theme

@Composable
fun SignUpScreen() {
    var userName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    var errorMessage by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text="Sign Up",
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
                .padding(bottom = 16.dp),
            singleLine = true
        )

        OutlinedTextField(
            value=confirmPassword,
            onValueChange= { text ->
                confirmPassword=text
            },
            label = {
                Text("Confirm Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 24.dp),
            singleLine = true
        )

        Button(
            onClick = {
                val usernameCheck = isValidUsername(userName)
                val passwordCheck = isValidPassword(password)
                if(usernameCheck.errorCode != 0) {
                    errorMessage = usernameCheck.errorMessage
                }else if(passwordCheck.errorCode != 0) {
                    errorMessage = passwordCheck.errorMessage
                }else if(password != confirmPassword) {
                    errorMessage="Passwords do not match"
                //  Add other else ifs for future conditions
                }else{
                    errorMessage="Looks Good, Time for logic"
                    // TODO: Actual sign up logic ;-;
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text="Submit", fontSize = 20.sp)
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text=errorMessage,
                color=Color.Red,
                fontSize = 16.sp,
                modifier = Modifier.padding(top=12.dp)
            )
        }
    }
}

data class ValidationResult(val errorCode: Int, val errorMessage: String)

fun isValidUsername(userName: String): ValidationResult {
    if (userName.isBlank()) {
        return ValidationResult(1, "Username cannot be empty")
    }

    // Add more username restrictions here if you want

    return ValidationResult(0, "")
}

fun isValidPassword(password: String): ValidationResult {
    if (password.isBlank()) {
        return ValidationResult(1, "Password cannot be empty")
    }

    // Add more password restrictions here if you want

    return ValidationResult(0, "")
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    CST438Project1Theme {
        SignUpScreen()
    }
}
