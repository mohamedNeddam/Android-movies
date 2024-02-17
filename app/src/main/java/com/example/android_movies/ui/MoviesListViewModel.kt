package com.example.android_movies.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_movies.domaine.models.Movie
import com.example.android_movies.network.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesListViewModel: ViewModel() {
    private val moviesRepository: MoviesRepository = MoviesRepository()


    var moviesList: MoviesState by mutableStateOf(MoviesState.Loading)
        private set

    init {
        getMovies()
    }

    private fun getMovies(){
        viewModelScope.launch {
            moviesList = try {
                val remoteMovies = getRemoteMovies()
                if (remoteMovies != null)
                    MoviesState.Success(remoteMovies)
                else MoviesState.Error
            }catch (exception : Exception){
                MoviesState.Error
            }
        }
    }

    private suspend fun getRemoteMovies() = withContext(Dispatchers.IO){
        moviesRepository.getMoviesListByQuery("Game")
    }

}

sealed interface MoviesState{
    data class Success(val movies: List<Movie>) : MoviesState
    object Error : MoviesState
    object Loading : MoviesState
}