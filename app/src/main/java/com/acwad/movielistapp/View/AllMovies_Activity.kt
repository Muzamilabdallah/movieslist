package com.acwad.movielistapp.View


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView

import com.acwad.movielistapp.Repositories.MoviesRepo
import com.acwad.movielistapp.Utils.Alertdialog
import com.acwad.movielistapp.Utils.coroutinse
import com.acwad.movielistapp.Utils.isConnected
import com.acwad.movielistapp.Utils.progress
import com.acwad.movielistapp.Utils.progress.showprogress


import com.acwad.movielistapp.data.local_data.AppDatabase
import com.acwad.movielistapp.data.remote_data.ServiceBuilder
import com.acwad.movielistapp.models.Movie
import com.acwad.show_employee.MoviesAdapter
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


import org.koin.android.ext.android.inject
import org.koin.core.context.GlobalContext.get


class AllMovies_Activity : AppCompatActivity(), Callback {

    lateinit var sort: String
    lateinit var gridLayoutManager: GridLayoutManager
    private var hud: KProgressHUD? = null

    lateinit var viewmodel: MovieViewModel
    lateinit var recyclerView: RecyclerView


    //    injecting factory viewmodel
    val factory: viewmodelfactory by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.acwad.movielistapp.R.layout.activity_main)




        var toolbar = findViewById<Toolbar>(com.acwad.movielistapp.R.id.toolbar)

        setSupportActionBar(toolbar)


        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)


        var spinner = findViewById<Spinner>(com.acwad.movielistapp.R.id.sort)


//initializing recyclerview
        recyclerView = findViewById(com.acwad.movielistapp.R.id.movielist)

        gridLayoutManager = GridLayoutManager(this, 2)


        recyclerView.layoutManager = gridLayoutManager


//getting view mofel instance

        viewmodel = ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)

        viewmodel.callback = this


//initializing spinner

        spinner.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener {


            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

                sort = spinner.getItemAtPosition(position).toString()


                fetchdata(sort)


            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }

        }


    }


//    fecthing data from data base  whether you're online or offline

    fun fetchdata(sort: String) {


        if (isConnected()) {

            viewmodel.fecthmovies(sort)


        } else {


            coroutinse.main {

          val movies = viewmodel.result.await()

                movies.observe(this, Observer {
                    recyclerView.adapter = MoviesAdapter(it, this)

                })
            }


        }


    }


    override fun onSuccess(list: List<Movie>) {
        progress.dismissprogress()

        recyclerView.adapter = MoviesAdapter(list, this)


    }




    override fun onError(error: String) {
        progress.dismissprogress()
        Alertdialog.popup(error, this)

    }


    override fun onDestroy() {
        super.onDestroy()

    }


    override fun onloading() {

        showprogress(this)


    }


}


