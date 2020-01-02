package com.acwad.movielistapp.View

import com.acwad.movielistapp.models.Movie

interface Callback {

    fun onSuccess(list:List<Movie> )

    fun onError(error:String)

    fun onloading()
}