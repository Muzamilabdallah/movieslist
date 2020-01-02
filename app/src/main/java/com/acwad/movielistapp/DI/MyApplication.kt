package com.acwad.movielistapp.DI

import android.app.Application
import com.acwad.movielistapp.Repositories.MoviesRepo
import com.acwad.movielistapp.View.MovieViewModel
import com.acwad.movielistapp.View.viewmodelfactory
import com.acwad.movielistapp.data.local_data.AppDatabase
import com.acwad.movielistapp.data.remote_data.ServiceBuilder
import okhttp3.internal.Internal.instance
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {


    var listofModules = module {
         single{ ServiceBuilder}
        single{  AppDatabase(get())}
      single { MoviesRepo(get(),get()) }
        single{  viewmodelfactory(get())}


    }


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listofModules)
        }
    }
}