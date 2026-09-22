package com.example.cst438project1

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.cst438project1.ui.screens.LandingScreen
import com.example.cst438project1.ui.theme.CST438Project1Theme
import org.junit.Rule
import org.junit.Test

class LandingScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun landingScreenDisplaysCorrectly() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                LandingScreen(navController)
            }
        }

        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sign Up").assertIsDisplayed()
    }
}