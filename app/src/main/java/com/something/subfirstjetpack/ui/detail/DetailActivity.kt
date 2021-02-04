package com.something.subfirstjetpack.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.something.subfirstjetpack.data.source.remote.response.Movies
import com.something.subfirstjetpack.data.source.remote.response.TvShow
import com.something.subfirstjetpack.databinding.ActivityDetailBinding
import com.something.subfirstjetpack.network.ApiConfig
import com.something.subfirstjetpack.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private val factory = ViewModelFactory.getInstance(ApiConfig)
    private lateinit var binding: ActivityDetailBinding

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV = "extra_tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val data = intent.extras
        if (data != null){
            val movieId = data.getString(EXTRA_MOVIE)
            val tvId = data.getString(EXTRA_TV)
            binding.pbDetail.visibility = View.VISIBLE
            when{
                movieId != null -> {
                    viewModel.selectedMovie(movieId)
                    viewModel.getDetailMovies().observe(this, {listMovie ->
                        binding.pbDetail.visibility = View.GONE
                        setDataMovie(listMovie)
                    })
                }
                tvId != null -> {
                    viewModel.selectedTv(tvId)
                    viewModel.getDetailTvShow().observe(this, {listTv ->
                        binding.pbDetail.visibility = View.GONE
                        setDataTvShow(listTv)
                    })
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setDataMovie(listMovie: Movies) {
        binding.apply {
            detailTitle.text = listMovie.name
            detailOverview.text = listMovie.overview
            detailRelease.text = listMovie.firstAirDate
            detailVote.text = listMovie.voteAverage.toString()
        }
        supportActionBar?.title = listMovie.name
        loadImage("https://image.tmdb.org/t/p/w500${listMovie.posterPath}", binding.detailImg)
    }


    private fun setDataTvShow(listTv: TvShow) {
        binding.apply {
            detailTitle.text = listTv.name
            detailVote.text = listTv.voteAverage.toString()
            detailRelease.text = listTv.firstAirDate
            detailOverview.text = listTv.overview
        }
        supportActionBar?.title = listTv.name
        loadImage("https://image.tmdb.org/t/p/w500${listTv.posterPath}", binding.detailImg)
    }


    private fun loadImage(url: String, image: ImageView){
        Glide.with(this)
                .load(url)
                .apply(RequestOptions().override(130, 180))
                .into(image)
    }
}