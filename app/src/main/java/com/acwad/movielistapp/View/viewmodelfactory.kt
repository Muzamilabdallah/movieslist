package com.acwad.movielistapp.View

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acwad.movielistapp.Repositories.MoviesRepo

class viewmodelfactory(private  val moviesRepo: MoviesRepo):ViewModelProvider.NewInstanceFactory(){


    override fun <T:ViewModel?>create(modelclass:Class<T>):T {

        return MovieViewModel(moviesRepo) as T


    }
}