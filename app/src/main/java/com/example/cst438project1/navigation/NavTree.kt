package com.example.cst438project1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cst438project1.ui.screens.LandingScreen
import com.example.cst438project1.ui.screens.LoginScreen
import com.example.cst438project1.ui.screens.SignUpScreen

@Composable
fun NavTree(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "landing"
    ){
        composable("landing"){
            LandingScreen(navController)
        }
        composable("login"){
            LoginScreen(navController)
        }
        composable("signup"){
            SignUpScreen(navController)
        }
    }
}