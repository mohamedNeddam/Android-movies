package com.example.android_movies.domaine.models

data class Movie(
    val id: Int = 0,
    val name: String = "",
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String? = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val releaseDate: String = "",
){
    fun hasValidPoster(): Boolean = posterPath?.isNotEmpty() == true
}