package com.example.cst438project1.data.model

import com.google.gson.annotations.SerializedName

data class LastFmSearchResponse(
    @SerializedName("results") val results: SearchResults
)

data class SearchResults(
    @SerializedName("artistmatches") val artistMatches: ArtistMatches
)

data class ArtistMatches(
    @SerializedName("artist") val artists: List<Artist>
)

data class Artist(
    @SerializedName("name") val name: String,
    @SerializedName("listeners") val listeners: String,
    @SerializedName("mbid") val mbid: String,
    @SerializedName("url") val url: String,
    @SerializedName("image") val image: List<Image>
)

data class Image(
    @SerializedName("#text") val url: String,
    @SerializedName("size") val size: String
)
