package com.example.cst438project1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cst438project1.data.model.Artist
import com.example.cst438project1.ui.theme.CST438Project1Theme
import com.example.cst438project1.ui.viewmodel.AuthViewModel
import com.example.cst438project1.ui.viewmodel.HomeViewModel
import com.example.cst438project1.ui.viewmodel.ProfileViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel,
    authViewModel: AuthViewModel,
    viewModel: HomeViewModel = viewModel()
) {
    val searchResults by viewModel.searchResults.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val user by authViewModel.currentUser.collectAsState()
    LaunchedEffect(user) {
        user?.let { u ->
            profileViewModel.updateColor(Color(u.profileColor))
            profileViewModel.updateImage(u.profileImage)
        }
    }
    val selectedImage = user?.profileImage ?: profileViewModel.selectedImage.value
    val selectedColor = user?.profileColor?.let {Color(it)} ?: profileViewModel.selectedColor.value

    HomeScreenContent(
        navController = navController,
        searchQuery = viewModel.searchQuery,
        onSearchQueryChange = { viewModel.onSearchQueryChange(it) },
        onSearchClick = { viewModel.performSearch() },
        searchResults = searchResults,
        isLoading = isLoading,
        errorMessage = errorMessage,
        selectedImage = selectedImage,
        selectedColor = selectedColor
    )
}

@Composable
fun HomeScreenContent(
    navController: NavController,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    searchResults: List<Artist>,
    isLoading: Boolean,
    errorMessage: String?,
    selectedImage: Int,
    selectedColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Home",
                    fontSize = 30.sp
                )

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {

                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                    IconButton(
                        onClick = {
                            //TODO: change route to userprofilescreen
                            navController.navigate("profilepic")
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(selectedColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(selectedImage),
                                contentDescription = "Profile",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                label = { Text("Search Artists") },
                trailingIcon = {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(searchResults) { artist ->
                        ArtistItem(artist)
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun ArtistItem(artist: Artist) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = artist.name, style = MaterialTheme.typography.titleMedium)
        Text(text = "${artist.listeners} listeners", style = MaterialTheme.typography.bodySmall)
    }
}

