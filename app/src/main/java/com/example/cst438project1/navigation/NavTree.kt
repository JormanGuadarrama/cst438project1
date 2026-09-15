package com.example.cst438project1.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cst438project1.ui.screens.HomeScreen
import com.example.cst438project1.ui.screens.LandingScreen
import com.example.cst438project1.ui.screens.LoginScreen
import com.example.cst438project1.ui.screens.SignUpScreen
import com.example.cst438project1.ui.screens.ProfilePicScreen
import com.example.cst438project1.ui.viewmodel.ProfileViewModel

@Composable
fun NavTree(navController: NavHostController) {
    val profileViewModel: ProfileViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("landing") {
            LandingScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
        composable("home") {
            HomeScreen(navController, profileViewModel)
        }
        composable("profilepic") {
            ProfilePicScreen(navController, profileViewModel)
        }
    }
}
