package com.acwad.movielistapp.Repositories

import android.widget.Switch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.acwad.movielistapp.data.local_data.MovieDAO
import com.acwad.movielistapp.data.remote_data.ServiceBuilder
import com.acwad.movielistapp.Utils.coroutinse
import com.acwad.movielistapp.data.local_data.AppDatabase
import com.acwad.movielistapp.models.Movie
import com.acwad.movielistapp.models.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class MoviesRepo(private val servicebuider: ServiceBuilder, private val db: AppDatabase) {


    private val movies = MutableLiveData<List<Movie>>()

//    observing movieslist livadata to sure if their is any changes in remote data or not

    init {

        movies.observeForever {

            SaveMovies(it)

        }


    }


//    fetching data from remote server

    suspend fun GetMoviesFromApi(sort: String): Response<MovieResponse>? {
        var response: Response<MovieResponse>? = null

        if (sort.equals("popular")) {

            response = servicebuider().getPopularMovies()
            movies.postValue(response.body()!!.movies)

        } else if (sort.equals("top_rated")) {

            response = servicebuider().getTopRatedMovies()
            movies.postValue(response.body()!!.movies)
        } else {

        }


        return response


    }


//    saving data locally

    fun SaveMovies(movies: List<Movie>) {

        coroutinse.IO {

            db.getmoviesdao().Insertmovie(movies)


        }


    }


//    fetching data from local database

    suspend fun GetLocalMovies(): LiveData<List<Movie>> {


        return withContext(Dispatchers.IO) {

            db.getmoviesdao().getAll()
        }


    }


}