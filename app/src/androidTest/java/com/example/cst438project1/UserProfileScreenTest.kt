package com.example.cst438project1

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.example.cst438project1.data.local.UserEntity
import com.example.cst438project1.ui.screens.UserProfileScreen
import com.example.cst438project1.ui.viewmodel.AuthViewModel
import com.example.cst438project1.ui.viewmodel.ProfileViewModel
import org.junit.Rule
import org.junit.Test

class UserProfileScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun userProfileScreenDisplaysCorrectly() {
        val application = ApplicationProvider.getApplicationContext<Application>()
        val authViewModel = AuthViewModel(application)
        val profileViewModel = ProfileViewModel()

        authViewModel.setCurrentUser(
            UserEntity(
                id = 1,
                username = "RandomUser",
                password = "test",
                profileImage = android.R.drawable.ic_menu_camera,
                profileColor = 0xFF00FF00,
                recentSearch = "Song1,Song2"
            )
        )
        profileViewModel.updateImage(android.R.drawable.ic_menu_camera)
        profileViewModel.updateColor(Color(0xFF00FF00))

        composeTestRule.setContent {
            val navController = rememberNavController()
            UserProfileScreen(
                navController = navController,
                profileViewModel = profileViewModel,
                authViewModel = authViewModel
            )
        }
        composeTestRule.onNodeWithText("RandomUser").assertIsDisplayed()
        composeTestRule.onNodeWithText("Recent Searches").assertIsDisplayed()
        composeTestRule.onNodeWithText("Change Profile Picture").assertIsDisplayed()
        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()
        composeTestRule.onNodeWithText("Back").assertIsDisplayed()
    }
}
