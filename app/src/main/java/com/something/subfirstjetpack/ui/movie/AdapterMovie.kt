package com.something.subfirstjetpack.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.something.subfirstjetpack.data.source.remote.response.Movies
import com.something.subfirstjetpack.databinding.ItemsKatalogBinding
import com.something.subfirstjetpack.ui.action.ItemClickCallback
import com.something.subfirstjetpack.ui.detail.DetailActivity

class AdapterMovie : RecyclerView.Adapter<AdapterMovie.CardViewHolder>(){

    private val listMovie = ArrayList<Movies>()

    fun setMovie(movie: List<Movies>){
        if (movie.isNullOrEmpty()) return
            this.listMovie.clear()
            this.listMovie.addAll(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemsKatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

    inner class CardViewHolder(private val binding: ItemsKatalogBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movies){
            with(binding){
                tvTitle.text = movie.name
                tvRelease.text = movie.firstAirDate
                tvDescription.text = movie.overview

                itemView.setOnClickListener(ItemClickCallback(object : ItemClickCallback.OnItemClickCallback{
                    override fun onItemClicked(v: View) {
                        val moveToDetail = Intent(itemView.context, DetailActivity::class.java)
                        moveToDetail.putExtra(DetailActivity.EXTRA_MOVIE, movie.id.toString())
                        itemView.context.startActivity(moveToDetail)
                    }
                }))

                Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                        .apply(RequestOptions().override(100, 150))
                        .into(binding.imgList)
            }
        }
    }
}