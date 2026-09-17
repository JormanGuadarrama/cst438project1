package com.example.cst438project1

import android.app.Application
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.example.cst438project1.ui.screens.SignUpScreen
import com.example.cst438project1.ui.theme.CST438Project1Theme
import com.example.cst438project1.ui.viewmodel.AuthViewModel
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun signUpScreenDisplaysCorrectly() {
        val application =
            ApplicationProvider.getApplicationContext<Application>()

        val authViewModel = AuthViewModel(application)

        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                SignUpScreen(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
        }

        composeTestRule.onNodeWithText("Sign Up").assertIsDisplayed()
        composeTestRule.onNodeWithText("Username").assertIsDisplayed()
        composeTestRule.onAllNodesWithText("Password")[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Confirm Password").assertIsDisplayed()
        composeTestRule.onNodeWithText("Submit").assertIsDisplayed()
        composeTestRule.onNodeWithText("Back").assertIsDisplayed()
    }

    @Test
    fun emptySignUpFieldsDisplayErrors() {
        val application =
            ApplicationProvider.getApplicationContext<Application>()

        val authViewModel = AuthViewModel(application)

        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                SignUpScreen(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
        }

        composeTestRule
            .onNodeWithText("Submit")
            .performClick()

        composeTestRule
            .onNodeWithText("Username cannot be empty")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Password cannot be empty")
            .assertIsDisplayed()
    }

    @Test
    fun differentPasswordsDisplayError() {
        val application =
            ApplicationProvider.getApplicationContext<Application>()

        val authViewModel = AuthViewModel(application)

        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                SignUpScreen(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
        }

        composeTestRule
            .onNodeWithText("Username")
            .performTextInput("testuser")

        composeTestRule
            .onAllNodesWithText("Password")[0]
            .performTextInput("password123")

        composeTestRule
            .onNodeWithText("Confirm Password")
            .performTextInput("differentpassword")

        composeTestRule
            .onNodeWithText("Submit")
            .performClick()

        composeTestRule
            .onNodeWithText("Passwords do not match")
            .assertIsDisplayed()
    }
}