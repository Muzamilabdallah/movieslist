package com.acwad.movielistapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.acwad.movielistapp.R
import com.acwad.movielistapp.Utils.constant
import com.squareup.picasso.Picasso

class MovieDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        var intent = this.intent

        var original_title = intent.getStringExtra("original_title")
        var posterPath = intent.getStringExtra("posterPath")
        var overview = intent.getStringExtra("overview")
        var voteAverage = intent.getDoubleExtra("voteAverage", 0.0)



        var original_titletxt = findViewById<TextView>(R.id.originaltitle)
        var oviewviewtxt = findViewById<TextView>(R.id.overview)

        var ratetxt = findViewById<TextView>(R.id.voterate)





        var imageview = findViewById<ImageView>(R.id.path)



//        setting values


        ratetxt?.text=voteAverage.toString()

        original_titletxt?.text = original_title

        oviewviewtxt?.text = overview
        ratetxt?.text = voteAverage.toString()


        val picasso = Picasso.get()

        picasso.load(constant.IMAGE_BASE_URL + posterPath).into(imageview)


    }
}
