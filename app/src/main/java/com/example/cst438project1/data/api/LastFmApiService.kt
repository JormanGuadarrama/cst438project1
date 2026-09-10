package com.example.cst438project1.data.api

import com.example.cst438project1.data.model.LastFmSearchResponse
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
