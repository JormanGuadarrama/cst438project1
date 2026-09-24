package com.example.cst438project1

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.example.cst438project1.ui.screens.HomeScreenContent
import com.example.cst438project1.ui.theme.CST438Project1Theme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreenDisplaysMainUi() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                HomeScreenContent(
                    navController = navController,
                    searchQuery = "",
                    onSearchQueryChange = {},
                    onSearchClick = {},
                    onArtistClick = {},
                    searchResults = emptyList(),
                    selectedArtist = null,
                    selectedAlbumImageUrl = null,
                    selectedAlbumName = null,
                    selectedArtistBio = null,
                    selectedArtistTags = emptyList(),
                    isLoading = false,
                    errorMessage = null,
                    selectedImage = R.drawable.profile_bunny,
                    selectedColor = Color.Red
                )
            }
        }

        composeTestRule
            .onNodeWithText("Home")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Search Artists")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("Settings")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("Profile")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("Search")
            .assertIsDisplayed()
    }

    @Test
    fun typingInSearchUpdatesSearchValue() {
        var searchValue = ""

        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                HomeScreenContent(
                    navController = navController,
                    searchQuery = searchValue,
                    onSearchQueryChange = { value ->
                        searchValue = value
                    },
                    onSearchClick = {},
                    onArtistClick = {},
                    searchResults = emptyList(),
                    selectedArtist = null,
                    selectedAlbumImageUrl = null,
                    selectedAlbumName = null,
                    selectedArtistBio = null,
                    selectedArtistTags = emptyList(),
                    isLoading = false,
                    errorMessage = null,
                    selectedImage = R.drawable.profile_bunny,
                    selectedColor = Color.Red
                )
            }
        }

        composeTestRule
            .onNode(hasSetTextAction() and hasText("Search Artists"))
            .performTextInput("Radiohead")

        assertEquals("Radiohead", searchValue)
    }

    @Test
    fun clickingSearchCallsSearchFunction() {
        var searchClicked = false

        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                HomeScreenContent(
                    navController = navController,
                    searchQuery = "",
                    onSearchQueryChange = {},
                    onSearchClick = {
                        searchClicked = true
                    },
                    onArtistClick = {},
                    searchResults = emptyList(),
                    selectedArtist = null,
                    selectedAlbumImageUrl = null,
                    selectedAlbumName = null,
                    selectedArtistBio = null,
                    selectedArtistTags = emptyList(),
                    isLoading = false,
                    errorMessage = null,
                    selectedImage = R.drawable.profile_bunny,
                    selectedColor = Color.Red
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Search")
            .performClick()

        assertTrue(searchClicked)
    }

    @Test
    fun homeScreenDisplaysErrorMessage() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            CST438Project1Theme {
                HomeScreenContent(
                    navController = navController,
                    searchQuery = "",
                    onSearchQueryChange = {},
                    onSearchClick = {},
                    onArtistClick = {},
                    searchResults = emptyList(),
                    selectedArtist = null,
                    selectedAlbumImageUrl = null,
                    selectedAlbumName = null,
                    selectedArtistBio = null,
                    selectedArtistTags = emptyList(),
                    isLoading = false,
                    errorMessage = "Search failed",
                    selectedImage = R.drawable.profile_bunny,
                    selectedColor = Color.Red
                )
            }
        }

        composeTestRule
            .onNodeWithText("Search failed")
            .assertIsDisplayed()
    }
}