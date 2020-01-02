package com.acwad.movielistapp.data.local_data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.acwad.movielistapp.models.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
    abstract class AppDatabase:RoomDatabase(){

    abstract  fun getmoviesdao():MovieDAO

    companion object{

        @Volatile
        private var INSTANCE:AppDatabase?=null


        private val  lock=Any()



        operator  fun invoke(context: Context)= INSTANCE?: synchronized(lock){


            INSTANCE?:buildDatabase(context)
        }

        private fun buildDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "movie_database"
        ).build()




    }
}