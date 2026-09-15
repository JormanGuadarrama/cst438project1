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

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    fun performSearch() {
        if (searchQuery.isBlank()) return

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val result = repository.searchArtists(searchQuery, limit = 3)
            
            result.onSuccess { artists ->
                _searchResults.value = artists
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }
            
            _isLoading.value = false
        }
    }
}
