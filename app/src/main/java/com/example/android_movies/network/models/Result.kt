package com.example.android_movies.network.models

data class Result(
    val adult: Boolean = false,
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val id: Int = 0,
    val origin_country: List<String> = listOf(),
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String? = "",
    val first_air_date: String = "",
    val name: String = "",
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)