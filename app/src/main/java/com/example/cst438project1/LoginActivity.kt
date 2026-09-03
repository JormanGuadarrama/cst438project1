package com.example.cst438project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cst438project1.ui.screens.LoginScreen
import com.example.cst438project1.ui.theme.CST438Project1Theme

class LoginActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CST438Project1Theme {
                LoginScreen()
            }
        }
    }
}