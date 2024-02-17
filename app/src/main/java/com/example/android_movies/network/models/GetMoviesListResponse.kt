package com.example.android_movies.network.models

data class GetMoviesListResponse(
    val page: Int = 0,
    val results: List<Result> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)