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

data class LastFmTopAlbumsResponse(
    @SerializedName("topalbums") val topAlbums: TopAlbums
)

data class TopAlbums(
    @SerializedName("album") val albums: List<Album>
)

data class Album(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: List<Image>
)
