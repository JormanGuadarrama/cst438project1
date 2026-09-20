package com.example.cst438project1

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.cst438project1.ui.screens.SettingsScreen
import com.example.cst438project1.ui.theme.CST438Project1Theme
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SettingsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun settingsScreenDisplaysOptions() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                SettingsScreen(
                    navController = navController,
                    isDarkTheme = false,
                    onThemeChange = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()
        composeTestRule.onNodeWithText("Appearance").assertIsDisplayed()
        composeTestRule.onNodeWithText("Light mode").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dark mode").assertIsDisplayed()
        composeTestRule.onNodeWithText("Back").assertIsDisplayed()
    }

    @Test
    fun lightModeIsSelectedWhenDarkModeIsFalse() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                SettingsScreen(
                    navController = navController,
                    isDarkTheme = false,
                    onThemeChange = {}
                )
            }
        }

        composeTestRule
            .onNodeWithTag("light_mode_option")
            .assertIsSelected()
    }

    @Test
    fun darkModeIsSelectedWhenDarkModeIsTrue() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme(darkTheme = true) {
                SettingsScreen(
                    navController = navController,
                    isDarkTheme = true,
                    onThemeChange = {}
                )
            }
        }

        composeTestRule
            .onNodeWithTag("dark_mode_option")
            .assertIsSelected()
    }

    @Test
    fun clickingDarkModeChangesThemeValue() {
        var darkTheme = false

        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                SettingsScreen(
                    navController = navController,
                    isDarkTheme = darkTheme,
                    onThemeChange = { value ->
                        darkTheme = value
                    }
                )
            }
        }

        composeTestRule
            .onNodeWithTag("dark_mode_option")
            .performClick()

        assertTrue(darkTheme)
    }

    @Test
    fun clickingLightModeChangesThemeValue() {
        var darkTheme = true

        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                SettingsScreen(
                    navController = navController,
                    isDarkTheme = darkTheme,
                    onThemeChange = { value ->
                        darkTheme = value
                    }
                )
            }
        }

        composeTestRule
            .onNodeWithTag("light_mode_option")
            .performClick()

        assertFalse(darkTheme)
    }

    @Test
    fun changePasswordButtonIsDisplayed() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                SettingsScreen(
                    navController = navController,
                    isDarkTheme = false,
                    onThemeChange = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText("Change Password")
            .assertIsDisplayed()
    }

    @Test
    fun deleteAccountButtonIsDisplayed() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                SettingsScreen(
                    navController = navController,
                    isDarkTheme = false,
                    onThemeChange = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText("Delete Account")
            .assertIsDisplayed()
    }
}