package com.acwad.show_employee

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.acwad.movielistapp.R
import com.acwad.movielistapp.Utils.constant
import com.acwad.movielistapp.Utils.constant.IMAGE_BASE_URL
import com.acwad.movielistapp.View.MovieDetail
import com.acwad.movielistapp.models.Movie
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


class MoviesAdapter(val list: List<Movie>, val context: Context) :
    RecyclerView.Adapter<MoviesAdapter.viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        return viewholder(
            LayoutInflater.from(context).inflate(
                R.layout.movie_item_row,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        var list = list.get(position)




        holder?.title.text = list.title
        holder?.year.text=list.releaseDate


        val picasso = Picasso.get()

        picasso.load(IMAGE_BASE_URL + list.posterPath).networkPolicy(NetworkPolicy.OFFLINE).into(holder.image)

        holder.image.setOnClickListener {

            var intent = Intent(context, MovieDetail::class.java)

            intent.putExtra("original_title", list.originalTitle)
            intent.putExtra("posterPath", list.posterPath)
            intent.putExtra("voteAverage", list.vote_average)

            intent.putExtra("overview", list.overview)
            intent.putExtra("releaseDate", list.releaseDate)

            context.startActivity(intent)


        }

    }


    class viewholder(view: View) : RecyclerView.ViewHolder(view) {


        var title = view.findViewById<TextView>(R.id.title)
        var year = view.findViewById<TextView>(R.id.year)
        var image = view.findViewById<ImageView>(R.id.poster_path)


    }

}




