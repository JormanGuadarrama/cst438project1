package com.example.cst438project1.data.api

import com.example.cst438project1.data.model.LastFmArtistInfoResponse
import com.example.cst438project1.data.model.LastFmSearchResponse
import com.example.cst438project1.data.model.LastFmSimilarArtistsResponse
import com.example.cst438project1.data.model.LastFmTopAlbumsResponse
import com.example.cst438project1.data.model.LastFmTopTracksResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("2.0/")
    suspend fun getTopAlbums(
        @Query("method") method: String = "artist.gettopalbums",
        @Query("artist") artist: String,
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int = 5,
        @Query("format") format: String = "json"
    ): Response<LastFmTopAlbumsResponse>

    @GET("2.0/")
    suspend fun getTopTracks(
        @Query("method") method: String = "artist.gettoptracks",
        @Query("artist") artist: String,
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int = 5,
        @Query("format") format: String = "json"
    ): Response<LastFmTopTracksResponse>

    @GET("2.0/")
    suspend fun getSimilarArtists(
        @Query("method") method: String = "artist.getsimilar",
        @Query("artist") artist: String,
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int = 3,
        @Query("format") format: String = "json"
    ): Response<LastFmSimilarArtistsResponse>

    @GET("2.0/")
    suspend fun getArtistInfo(
        @Query("method") method: String = "artist.getinfo",
        @Query("artist") artist: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json"
    ): Response<LastFmArtistInfoResponse>

    @FormUrlEncoded
    @POST("2.0/")
    suspend fun getMobileSession(
        @Field("method") method: String = "auth.getMobileSession",
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("api_key") apiKey: String,
        @Field("api_sig") apiSig: String,
        @Field("format") format: String = "json"
    ): Response<ResponseBody> // We'll map this to a model later

    companion object {
        const val BASE_URL = "https://ws.audioscrobbler.com/"
    }
}
