package com.example.cst438project1

import android.app.Application
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.example.cst438project1.ui.screens.LoginScreen
import com.example.cst438project1.ui.theme.CST438Project1Theme
import com.example.cst438project1.ui.viewmodel.AuthViewModel
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginScreenDisplaysCorrectly() {
        val application =
            ApplicationProvider.getApplicationContext<Application>()

        val authViewModel = AuthViewModel(application)

        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                LoginScreen(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
        }

        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
        composeTestRule.onNodeWithText("Username").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
        composeTestRule.onNodeWithText("Submit").assertIsDisplayed()
        composeTestRule.onNodeWithText("Back").assertIsDisplayed()
    }

    @Test
    fun loginWithEmptyFieldsDisplaysError() {
        val application =
            ApplicationProvider.getApplicationContext<Application>()

        val authViewModel = AuthViewModel(application)

        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                LoginScreen(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
        }

        composeTestRule
            .onNodeWithText("Submit")
            .performClick()

        composeTestRule
            .onNodeWithText("Username and password cannot be empty")
            .assertIsDisplayed()
    }
}