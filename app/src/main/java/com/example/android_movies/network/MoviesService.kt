package com.example.android_movies.network

import com.example.android_movies.network.models.Credentials
import com.example.android_movies.network.models.GetMoviesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("search/tv")
    suspend fun getMoviesListByQuery(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = Credentials.API_KEY
    ): Response<GetMoviesListResponse>

}