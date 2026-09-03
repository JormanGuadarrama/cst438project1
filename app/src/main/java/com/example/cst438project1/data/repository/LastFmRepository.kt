package com.example.cst438project1.data.repository

import com.example.cst438project1.BuildConfig
import com.example.cst438project1.data.api.LastFmApiService
import com.example.cst438project1.data.model.Artist
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class LastFmRepository(private val apiService: LastFmApiService) {

    private val searchMutex = Mutex()

    suspend fun searchArtists(query: String, limit: Int = 10): Result<List<Artist>> {
        if (query.length < 3) {
            return Result.failure(Exception("Query must be at least 3 characters"))
        }

        return searchMutex.withLock {
            try {
                val response = apiService.searchArtists(
                    artist = query,
                    apiKey = BuildConfig.LASTFM_API_KEY,
                    limit = limit
                )

                if (response.isSuccessful) {
                    val artists = response.body()?.results?.artistMatches?.artists ?: emptyList()
                    Result.success(artists)
                } else {
                    val errorCode = response.code()
                    val errorMessage = when (errorCode) {
                        429 -> "Rate limit exceeded. Please try again later."
                        else -> "Last.fm API error: ${response.message()}"
                    }
                    Result.failure(Exception(errorMessage))
                }
            } catch (e: Exception) {
                Result.failure(Exception("Network failure. Please check your connection."))
            }
        }
    }
}
