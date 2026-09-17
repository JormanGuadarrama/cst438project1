package com.example.cst438project1.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cst438project1.data.api.RetrofitClient
import com.example.cst438project1.data.model.Artist
import com.example.cst438project1.data.repository.LastFmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = LastFmRepository(RetrofitClient.apiService)

    var searchQuery by mutableStateOf("")
        private set

    private val _searchResults = MutableStateFlow<List<Artist>>(emptyList())
    val searchResults: StateFlow<List<Artist>> = _searchResults

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _selectedArtist = MutableStateFlow<Artist?>(null)
    val selectedArtist: StateFlow<Artist?> = _selectedArtist

    private val _selectedAlbumImageUrl = MutableStateFlow<String?>(null)
    val selectedAlbumImageUrl: StateFlow<String?> = _selectedAlbumImageUrl

    private val _selectedArtistBio = MutableStateFlow<String?>(null)
    val selectedArtistBio: StateFlow<String?> = _selectedArtistBio

    private val _selectedAlbumName = MutableStateFlow<String?>(null)
    val selectedAlbumName: StateFlow<String?> = _selectedAlbumName

    private val _selectedArtistTags = MutableStateFlow<List<String>>(emptyList())
    val selectedArtistTags: StateFlow<List<String>> = _selectedArtistTags

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    fun onArtistClick(artist: Artist) {
        _selectedArtist.value = artist
        _searchResults.value = emptyList() // Clear results
        
        viewModelScope.launch {
            // Fetch Top Album for the image and name
            repository.getTopAlbum(artist.name).onSuccess { album ->
                val url = album?.image?.find { it.size == "extralarge" }?.url
                    ?: album?.image?.lastOrNull()?.url
                _selectedAlbumImageUrl.value = url
                _selectedAlbumName.value = album?.name
            }

            // Fetch Artist Info for bio and tags
            repository.getArtistInfo(artist.name).onSuccess { artistDetail ->
                if (artistDetail != null) {
                    // Extract first paragraph of bio
                    val rawBio = artistDetail.bio.content
                    val firstParagraph = rawBio
                        .substringBefore("\n\n")
                        .substringBefore("<a href")
                        .trim()
                    _selectedArtistBio.value = firstParagraph

                    // Extract top 3 tags
                    _selectedArtistTags.value = artistDetail.tags.tag.take(3).map { it.name }
                }
            }
        }
    }

    fun performSearch() {
        if (searchQuery.isBlank()) return

        // Clear previous selection
        _selectedArtist.value = null
        _selectedAlbumImageUrl.value = null
        _selectedAlbumName.value = null
        _selectedArtistBio.value = null
        _selectedArtistTags.value = emptyList()

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val result = repository.searchArtists(searchQuery, limit = 3)
            
            result.onSuccess { artists ->
                artists.forEach { artist ->
                    println("Search result - Artist: ${artist.name}, Image URL: ${artist.image.find { it.size == "extralarge" }?.url}")
                }
                _searchResults.value = artists
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }
            
            _isLoading.value = false
        }
    }
}
