package com.something.subfirstjetpack.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.something.subfirstjetpack.data.source.remote.response.TvShow
import com.something.subfirstjetpack.databinding.ItemsKatalogBinding
import com.something.subfirstjetpack.ui.action.ItemClickCallback
import com.something.subfirstjetpack.ui.detail.DetailActivity

class AdapterTvShow : RecyclerView.Adapter<AdapterTvShow.CardViewHolder>(){

    private val listTvShow = ArrayList<TvShow>()

    fun setMovie(tv: List<TvShow>){
        if (tv.isNullOrEmpty()) return
            this.listTvShow.clear()
            this.listTvShow.addAll(tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemsKatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class CardViewHolder(private val binding: ItemsKatalogBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvShow){
            with(binding){

                tvTitle.text = tv.name
                tvRelease.text = tv.firstAirDate
                tvDescription.text = tv.overview

                itemView.setOnClickListener(ItemClickCallback(object : ItemClickCallback.OnItemClickCallback{
                    override fun onItemClicked(v: View) {
                        val moveToDetail = Intent(itemView.context, DetailActivity::class.java)
                        moveToDetail.putExtra(DetailActivity.EXTRA_TV, tv.id.toString())
                        itemView.context.startActivity(moveToDetail)
                    }
                }))

                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${tv.posterPath}")
                    .apply(RequestOptions().override(100, 150))
                    .into(binding.imgList)

            }
        }
    }

}