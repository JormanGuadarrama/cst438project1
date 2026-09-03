package com.example.cst438project1.data.api

import com.example.cst438project1.data.model.LastFmSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmApiService {
    @GET("2.0/")
    suspend fun searchArtists(
        @Query("method") method: String = "artist.search",
        @Query("artist") artist: String,
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int = 10,
        @Query("format") format: String = "json"
    ): Response<LastFmSearchResponse>

    companion object {
        const val BASE_URL = "https://ws.audioscrobbler.com/"
    }
}
