package com.example.android_movies.domaine.mappers

import com.example.android_movies.domaine.models.Movie
import com.example.android_movies.network.models.Result

object MovieMapper {
    private fun buildFrom(response: Result): Movie {
        return Movie(
            id = response.id,
            name = response.name,
            originalName = response.original_name,
            overview = response.overview,
            popularity = response.popularity,
            posterPath = response.poster_path,
            voteAverage = response.vote_average,
            voteCount = response.vote_count,
            releaseDate = response.first_air_date
        )
    }

    fun mapResultsToMovies(results: List<Result>): List<Movie> {
        return results.map { buildFrom(it) }
    }
}