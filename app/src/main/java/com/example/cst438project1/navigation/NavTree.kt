package com.example.cst438project1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cst438project1.ui.screens.HomeScreen
import com.example.cst438project1.ui.screens.LandingScreen
import com.example.cst438project1.ui.screens.LoginScreen
import com.example.cst438project1.ui.screens.ProfilePicScreen
import com.example.cst438project1.ui.screens.ProfileViewModel
import com.example.cst438project1.ui.screens.SignUpScreen
import com.example.cst438project1.ui.screens.UserProfileScreen
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun NavTree(navController: NavHostController){
    val profileViewModel: ProfileViewModel = viewModel()
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
        composable("home"){
            HomeScreen(navController, profileViewModel)
        }
        composable("profilepic"){
            ProfilePicScreen(navController, profileViewModel)
        }
        composable("userprofilescreen"){
            UserProfileScreen(navController, profileViewModel)
        }
    }
}