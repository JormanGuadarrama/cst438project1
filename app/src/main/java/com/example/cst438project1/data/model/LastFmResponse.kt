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
    @SerializedName("listeners") val listeners: String? = null,
    @SerializedName("mbid") val mbid: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("image") val image: List<Image> = emptyList()
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

data class LastFmArtistInfoResponse(
    @SerializedName("artist") val artist: ArtistDetail
)

data class ArtistDetail(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: List<Image>,
    @SerializedName("bio") val bio: Bio,
    @SerializedName("tags") val tags: Tags
)

data class Tags(
    @SerializedName("tag") val tag: List<Tag>
)

data class Tag(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class Bio(
    @SerializedName("summary") val summary: String,
    @SerializedName("content") val content: String
)

data class LastFmTopTracksResponse(
    @SerializedName("toptracks") val topTracks: TopTracks
)

data class TopTracks(
    @SerializedName("track") val tracks: List<Track>
)

data class Track(
    @SerializedName("name") val name: String,
    @SerializedName("playcount") val playcount: String? = null,
    @SerializedName("listeners") val listeners: String? = null,
    @SerializedName("url") val url: String? = null
)

data class LastFmSimilarArtistsResponse(
    @SerializedName("similarartists") val similarArtists: SimilarArtists
)

data class SimilarArtists(
    @SerializedName("artist") val artists: List<Artist>
)
