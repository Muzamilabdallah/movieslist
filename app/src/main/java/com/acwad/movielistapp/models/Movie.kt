package com.acwad.movielistapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "movies")
data class Movie(
    @SerializedName("id")   @PrimaryKey(autoGenerate = true) val id: Long,
    @SerializedName("title") @ColumnInfo(name="title")val title: String,
    @SerializedName("overview") @ColumnInfo(name="overview")val overview: String,
    @SerializedName("poster_path") @ColumnInfo(name="posterPath") val posterPath: String,
    @SerializedName("backdrop_path") @ColumnInfo(name="backdropPath") val backdropPath: String,
    @SerializedName("vote_average")  @ColumnInfo(name="vote_average") val vote_average: Double,
    @SerializedName("release_date") @ColumnInfo(name="releaseDate") val releaseDate: String,
    @SerializedName("original_title") @ColumnInfo(name = "originalTitle") val  originalTitle:String



)


