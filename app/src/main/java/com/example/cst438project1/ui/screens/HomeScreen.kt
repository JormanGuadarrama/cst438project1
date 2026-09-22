package com.example.cst438project1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cst438project1.R
import com.example.cst438project1.data.model.Album
import com.example.cst438project1.data.model.Artist
import com.example.cst438project1.data.model.Track
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
    val selectedArtist by viewModel.selectedArtist.collectAsState()
    val selectedAlbumImageUrl by viewModel.selectedAlbumImageUrl.collectAsState()
    val selectedAlbumName by viewModel.selectedAlbumName.collectAsState()
    val selectedArtistBio by viewModel.selectedArtistBio.collectAsState()
    val selectedArtistTags by viewModel.selectedArtistTags.collectAsState()
    val selectedArtistTopTracks by viewModel.selectedArtistTopTracks.collectAsState()
    val selectedArtistOtherAlbums by viewModel.selectedArtistOtherAlbums.collectAsState()
    val selectedArtistSimilarArtists by viewModel.selectedArtistSimilarArtists.collectAsState()
    val user by authViewModel.currentUser.collectAsState()

    LaunchedEffect(user) {
        user?.let { u ->
            profileViewModel.updateColor(Color(u.profileColor))
            profileViewModel.updateImage(u.profileImage)
        }
    }
    val selectedImage = user?.profileImage ?: profileViewModel.selectedImage.value
    val selectedColor = user?.profileColor?.let { Color(it) } ?: profileViewModel.selectedColor.value

    HomeScreenContent(
        navController = navController,
        searchQuery = viewModel.searchQuery,
        onSearchQueryChange = { viewModel.onSearchQueryChange(it) },
        onSearchClick = {
            viewModel.performSearch()
            val currentUser = authViewModel.currentUser.value
            if (currentUser != null && viewModel.searchQuery.isNotBlank()) {
                authViewModel.addRecentSearch(currentUser.id, viewModel.searchQuery)
            }
        },
        onArtistClick = { viewModel.onArtistClick(it) },
        searchResults = searchResults,
        selectedArtist = selectedArtist,
        selectedAlbumImageUrl = selectedAlbumImageUrl,
        selectedAlbumName = selectedAlbumName,
        selectedArtistBio = selectedArtistBio,
        selectedArtistTags = selectedArtistTags,
        selectedArtistTopTracks = selectedArtistTopTracks,
        selectedArtistOtherAlbums = selectedArtistOtherAlbums,
        selectedArtistSimilarArtists = selectedArtistSimilarArtists,
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
    onArtistClick: (Artist) -> Unit,
    searchResults: List<Artist>,
    selectedArtist: Artist?,
    selectedAlbumImageUrl: String?,
    selectedAlbumName: String?,
    selectedArtistBio: String?,
    selectedArtistTags: List<String>,
    selectedArtistTopTracks: List<Track> = emptyList(),
    selectedArtistOtherAlbums: List<Album> = emptyList(),
    selectedArtistSimilarArtists: List<Artist> = emptyList(),
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
            Spacer(modifier = Modifier.height(25.dp))
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
                    IconButton(
                        onClick = {
                            navController.navigate("settings")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                    IconButton(
                        onClick = {
                            navController.navigate("profile")
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
            } else if (searchResults.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(searchResults) { artist ->
                        ArtistItem(artist, onArtistClick)
                        HorizontalDivider()
                    }
                }
            } else if (selectedArtist != null) {
                // Scrollable Recommendation Dashboard for Selected Artist
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    // Top Album & Bio Header Section
                    Text(
                        text = "Top Album",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(modifier = Modifier.width(150.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(150.dp)
                                    .background(Color.LightGray)
                            ) {
                                AsyncImage(
                                    model = selectedAlbumImageUrl,
                                    contentDescription = "Selected Artist Album",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            if (selectedAlbumName != null) {
                                Text(
                                    text = selectedAlbumName,
                                    style = MaterialTheme.typography.labelMedium,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = selectedArtist.name,
                                style = MaterialTheme.typography.headlineSmall
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(vertical = 4.dp)
                            ) {
                                selectedArtistTags.forEach { tag ->
                                    Text(
                                        text = "#$tag",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier
                                            .background(
                                                MaterialTheme.colorScheme.secondaryContainer,
                                                RoundedCornerShape(4.dp)
                                            )
                                            .padding(horizontal = 4.dp, vertical = 2.dp)
                                    )
                                }
                            }

                            if (selectedArtistBio != null) {
                                Text(
                                    text = selectedArtistBio,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }

                    // Top Tracks Section
                    if (selectedArtistTopTracks.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Top Tracks",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            selectedArtistTopTracks.forEachIndexed { index, track ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "${index + 1}.",
                                            style = MaterialTheme.typography.labelLarge,
                                            modifier = Modifier.width(28.dp)
                                        )
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = track.name,
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                            val plays = track.playcount ?: track.listeners
                                            if (plays != null) {
                                                Text(
                                                    text = "$plays plays",
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // More Albums Section (Albums 2 - 5)
                    if (selectedArtistOtherAlbums.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "More Albums",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(selectedArtistOtherAlbums) { album ->
                                Column(
                                    modifier = Modifier.width(110.dp)
                                ) {
                                    val albumUrl = album.image.find { (it.size == "extralarge") }?.url
                                        ?: album.image.lastOrNull()?.url
                                    Box(
                                        modifier = Modifier
                                            .size(110.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color.LightGray)
                                    ) {
                                        AsyncImage(
                                            model = albumUrl,
                                            contentDescription = album.name,
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                    Text(
                                        text = album.name,
                                        style = MaterialTheme.typography.labelMedium,
                                        maxLines = 2,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                            }
                        }
                    }

                    // Similar Artists Grid Section (3 artists)
                    if (selectedArtistSimilarArtists.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Similar Artists",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            selectedArtistSimilarArtists.take(3).forEach { similar ->
                                val imageUrl = similar.image.find { (it.size == "extralarge") }?.url
                                    ?: similar.image.lastOrNull()?.url

                                Card(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable { onArtistClick(similar) },
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(80.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(Color.LightGray)
                                        ) {
                                            AsyncImage(
                                                model = imageUrl,
                                                contentDescription = similar.name,
                                                modifier = Modifier.fillMaxSize(),
                                                contentScale = ContentScale.Crop
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = similar.name,
                                            style = MaterialTheme.typography.labelMedium,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun ArtistItem(artist: Artist, onClick: (Artist) -> Unit) {
    Button(
        onClick = {
            println("Button clicked for artist: ${artist.name}")
            onClick(artist)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = artist.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "${artist.listeners} listeners", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    CST438Project1Theme {
        HomeScreenContent(
            navController = navController,
            searchQuery = "Radiohead",
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
