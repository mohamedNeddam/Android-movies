package com.example.android_movies.ui.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.android_movies.ui.MoviesListViewModel
import com.example.android_movies.ui.MoviesState
import com.example.android_movies.domaine.models.Movie

@Composable
fun HomeScreen() {
    val moviesListViewModel: MoviesListViewModel = viewModel()

    when(moviesListViewModel.moviesList){
        is MoviesState.Error ->
            Row{
                Text(text = "Loading")
            }
        is MoviesState.Loading ->
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        is MoviesState.Success -> LazyColumn(){
            items((moviesListViewModel.moviesList as MoviesState.Success).movies){
                    movie -> MovieItem(movie)
            }
        }
    }


}


@Composable
fun MovieItem(movie: Movie){

    Box(modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 16.dp)
        .fillMaxWidth()
        .height(500.dp)
        .clip(RoundedCornerShape(bottomEnd = 25.dp))
    )
    {
        Card(modifier = Modifier.fillMaxSize()){
            Box {
                AsyncImage(model = "https://image.tmdb.org/t/p/w500/"+movie.posterPath,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(bottomEnd = 25.dp))
                )
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp), verticalArrangement = Arrangement.Bottom) {

                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .padding(top = 5.dp)) {
                        Text(movie.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(movie.overview.take(50) +" ...", fontSize = 10.sp)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text("${movie.voteCount} Vote | ${movie.releaseDate}",  fontWeight = FontWeight.Normal, fontSize = 10.sp)
                }
            }
        }
    }
}