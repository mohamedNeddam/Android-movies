package com.example.android_movies.network

import com.example.android_movies.domaine.mappers.MovieMapper
import com.example.android_movies.domaine.models.Movie

class MoviesRepository {

    suspend fun getMoviesListByQuery(query: String): List<Movie>?{
        val request = NetworkLayer.movieApi.getMoviesListByQuery(query)

        return if(request.failed || !request.isSuccess) null
            else MovieMapper.mapResultsToMovies(request.body.results)
    }
}