package com.acwad.movielistapp.View


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.acwad.movielistapp.Repositories.MoviesRepo
import com.acwad.movielistapp.Utils.LazyDeferred

import com.acwad.movielistapp.Utils.coroutinse
import com.acwad.movielistapp.models.Movie
import kotlinx.coroutines.*
import retrofit2.HttpException

class MovieViewModel(private val moviesRepo: MoviesRepo) : ViewModel() {

    lateinit var list: LiveData<List<Movie>>

    var callback: Callback? = null



//    calling function from repositories

    fun fecthmovies(  sort:String) {


        coroutinse.main {
            callback?.onloading()

            val response = moviesRepo.GetMoviesFromApi(sort)

            withContext(Dispatchers.Main) {

                try {


                    if (response!!.isSuccessful) {


                        callback?.onSuccess(response.body()?.movies!!)


                    } else {
                        callback?.onError("unable to fetch data")
                    }
                } catch (e: HttpException) {
                    callback!!.onError(e.toString())
                }

            }
        }



    }








    val result by LazyDeferred {


        moviesRepo.GetLocalMovies()


    }





}


