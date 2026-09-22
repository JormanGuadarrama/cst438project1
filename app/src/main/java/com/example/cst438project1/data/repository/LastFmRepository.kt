package com.example.cst438project1.data.repository

import com.example.cst438project1.BuildConfig
import com.example.cst438project1.data.api.LastFmApiService
import com.example.cst438project1.data.model.Album
import com.example.cst438project1.data.model.Artist
import com.example.cst438project1.data.model.ArtistDetail
import com.example.cst438project1.data.model.Track
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

    suspend fun getTopAlbums(artistName: String, limit: Int = 5): Result<List<Album>> {
        return try {
            val response = apiService.getTopAlbums(
                artist = artistName,
                apiKey = BuildConfig.LASTFM_API_KEY,
                limit = limit
            )
            if (response.isSuccessful) {
                Result.success(response.body()?.topAlbums?.albums ?: emptyList())
            } else {
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getTopAlbum(artistName: String): Result<Album?> {
        return getTopAlbums(artistName, limit = 1).map { it.firstOrNull() }
    }

    suspend fun getTopTracks(artistName: String, limit: Int = 5): Result<List<Track>> {
        return try {
            val response = apiService.getTopTracks(
                artist = artistName,
                apiKey = BuildConfig.LASTFM_API_KEY,
                limit = limit
            )
            if (response.isSuccessful) {
                Result.success(response.body()?.topTracks?.tracks ?: emptyList())
            } else {
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getSimilarArtists(artistName: String, limit: Int = 3): Result<List<Artist>> {
        return try {
            val response = apiService.getSimilarArtists(
                artist = artistName,
                apiKey = BuildConfig.LASTFM_API_KEY,
                limit = limit
            )
            if (response.isSuccessful) {
                Result.success(response.body()?.similarArtists?.artists ?: emptyList())
            } else {
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getArtistInfo(artistName: String): Result<ArtistDetail?> {
        return try {
            val response = apiService.getArtistInfo(
                artist = artistName,
                apiKey = BuildConfig.LASTFM_API_KEY
            )
            if (response.isSuccessful) {
                Result.success(response.body()?.artist)
            } else {
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
