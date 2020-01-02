package com.acwad.movielistapp.data.local_data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acwad.movielistapp.models.Movie
import com.acwad.movielistapp.models.MovieResponse

@Dao
   interface MovieDAO {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun Insertmovie(movie: List<Movie>)



    @Query("SELECT * FROM movies  ")
         fun getAll(): LiveData<List<Movie>>
}