package com.acwad.movielistapp.data

import androidx.lifecycle.LiveData

import com.acwad.movielistapp.models.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiClient {


    @GET("movie/popular")
    suspend fun getPopularMovies(

    ): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(

    ): Response<MovieResponse>

}