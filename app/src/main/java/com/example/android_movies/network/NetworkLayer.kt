package com.example.android_movies.network

import com.example.android_movies.network.models.Credentials
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val retrofit = Retrofit.Builder()
        .client(getLoggingHttpClient())
        .baseUrl(Credentials.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val moviesService: MoviesService by lazy {
        retrofit.create(MoviesService::class.java)
    }

    val movieApi = MovieApi(moviesService)

    // i added this for Debugging network requests i find some error but i cant see the logs of the network requests
    private fun getLoggingHttpClient(): OkHttpClient{
        val client = OkHttpClient.Builder()
        client.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel((HttpLoggingInterceptor.Level.BASIC))
        })

        return client.build()
    }
}