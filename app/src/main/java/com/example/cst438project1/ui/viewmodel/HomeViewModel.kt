package com.example.cst438project1.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cst438project1.data.api.RetrofitClient
import com.example.cst438project1.data.model.Album
import com.example.cst438project1.data.model.Artist
import com.example.cst438project1.data.model.Track
import com.example.cst438project1.data.repository.LastFmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.BreakIterator
import java.util.Locale

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

    private val _selectedArtistTopTracks = MutableStateFlow<List<Track>>(emptyList())
    val selectedArtistTopTracks: StateFlow<List<Track>> = _selectedArtistTopTracks

    private val _selectedArtistOtherAlbums = MutableStateFlow<List<Album>>(emptyList())
    val selectedArtistOtherAlbums: StateFlow<List<Album>> = _selectedArtistOtherAlbums

    private val _selectedArtistSimilarArtists = MutableStateFlow<List<Artist>>(emptyList())
    val selectedArtistSimilarArtists: StateFlow<List<Artist>> = _selectedArtistSimilarArtists

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    fun onArtistClick(artist: Artist) {
        _selectedArtist.value = artist
        _searchResults.value = emptyList() // Clear results
        _selectedArtistTopTracks.value = emptyList()
        _selectedArtistOtherAlbums.value = emptyList()
        _selectedArtistSimilarArtists.value = emptyList()

        viewModelScope.launch {
            // Fetch Top Albums (limit = 5) for top album + albums 2-5
            repository.getTopAlbums(artist.name, limit = 5).onSuccess { albums ->
                val firstAlbum = albums.firstOrNull()
                val url = firstAlbum?.image?.find { (it.size == "extralarge") }?.url
                    ?: firstAlbum?.image?.lastOrNull()?.url
                _selectedAlbumImageUrl.value = url
                _selectedAlbumName.value = firstAlbum?.name
                _selectedArtistOtherAlbums.value = albums.drop(1)
            }

            // Fetch Artist Info for bio and tags
            repository.getArtistInfo(artist.name).onSuccess { artistDetail ->
                if (artistDetail != null) {
                    val rawBio = artistDetail.bio.content.ifBlank { artistDetail.bio.summary }
                    _selectedArtistBio.value = formatBio(rawBio)

                    // Extract top 3 tags
                    _selectedArtistTags.value = artistDetail.tags.tag.take(3).map { it.name }
                }
            }

            // Fetch Top Tracks (limit = 5)
            repository.getTopTracks(artist.name, limit = 5).onSuccess { tracks ->
                _selectedArtistTopTracks.value = tracks
            }

            // Fetch Similar Artists (limit = 3) and fetch top album image for each
            repository.getSimilarArtists(artist.name, limit = 3).onSuccess { similar ->
                val similarWithAlbumArt = similar.take(3).map { similarArtist ->
                    val albumResult = repository.getTopAlbum(similarArtist.name)
                    val albumImages = albumResult.getOrNull()?.image ?: emptyList()
                    if (albumImages.isNotEmpty()) {
                        similarArtist.copy(image = albumImages)
                    } else {
                        similarArtist
                    }
                }
                _selectedArtistSimilarArtists.value = similarWithAlbumArt
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
        _selectedArtistTopTracks.value = emptyList()
        _selectedArtistOtherAlbums.value = emptyList()
        _selectedArtistSimilarArtists.value = emptyList()

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = repository.searchArtists(searchQuery, limit = 3)

            result.onSuccess { artists ->
                artists.forEach { artist ->
                    println("Search result - Artist: ${artist.name}, Image URL: ${artist.image.find { (it.size == "extralarge") }?.url}")
                }
                _searchResults.value = artists
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }

            _isLoading.value = false
        }
    }

    fun formatBio(rawBio: String): String {
        if (rawBio.isBlank()) return ""

        val textBeforeAnchor = rawBio.substringBefore("<a href")
        val cleanText = textBeforeAnchor
            .replace(Regex("<[^>]*>"), "")
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&apos;", "'")
            .replace("&lt;", "<")
            .replace("&gt;", ">")
            .replace("&#160;", " ")
            .replace("&nbsp;", " ")
            .replace(Regex("\\s+"), " ")
            .trim()

        if (cleanText.isEmpty()) return ""

        val iterator = BreakIterator.getSentenceInstance(Locale.US)
        iterator.setText(cleanText)

        val sentences = mutableListOf<String>()
        var start = iterator.first()
        var end = iterator.next()
        while (end != BreakIterator.DONE) {
            val sentence = cleanText.substring(start, end).trim()
            if (sentence.isNotEmpty()) {
                sentences.add(sentence)
            }
            start = end
            end = iterator.next()
        }

        if (sentences.isEmpty()) return ""

        val first = sentences[0]
        if (first.length > 300) {
            return truncateTo300(first)
        }

        if (sentences.size == 1) {
            return first
        }

        val second = sentences[1]
        val combined = "$first $second"
        return if (combined.length <= 300) {
            combined
        } else {
            first
        }
    }

    private fun truncateTo300(text: String): String {
        if (text.length <= 300) return text
        val truncated = text.take(300)
        val lastSpace = truncated.lastIndexOf(' ')
        return if (lastSpace > 0) {
            truncated.substring(0, lastSpace).trim()
        } else {
            truncated.trim()
        }
    }
}
