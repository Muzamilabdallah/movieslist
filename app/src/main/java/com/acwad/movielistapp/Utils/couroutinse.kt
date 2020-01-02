package com.acwad.movielistapp.Utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


object coroutinse {

    fun  main(work:suspend(()->Unit))=
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }


    fun  IO(work:suspend(()->Unit))=
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }


}