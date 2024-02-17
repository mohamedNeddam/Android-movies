package com.example.android_movies.network

import com.example.android_movies.network.models.GetMoviesListResponse
import com.example.android_movies.network.SimpleResponse
import retrofit2.Response

class MovieApi(
    private val moviesService: MoviesService
) {

    suspend fun getMoviesListByQuery(query: String): SimpleResponse<GetMoviesListResponse> {
        return safeApiCall { moviesService.getMoviesListByQuery(query) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        }catch (exception: Exception){
            SimpleResponse.failure(exception)
        }
    }
}