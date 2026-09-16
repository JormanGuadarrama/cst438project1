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
import com.example.cst438project1.ui.viewmodel.AuthViewModel
import com.example.cst438project1.ui.viewmodel.ProfileViewModel

@Composable
fun NavTree(navController: NavHostController) {
    val authViewModel: AuthViewModel = viewModel()
    val profileViewModel: ProfileViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "landing"
    ) {
        composable("landing") {
            LandingScreen(navController)
        }
        composable("login") {
            LoginScreen(navController,authViewModel)
        }
        composable("signup") {
            SignUpScreen(navController,authViewModel)
        }
        composable("home") {
            HomeScreen(navController, profileViewModel,authViewModel)
        }
        composable("profilepic") {
            ProfilePicScreen(navController, profileViewModel,authViewModel)
        }
    }
}
