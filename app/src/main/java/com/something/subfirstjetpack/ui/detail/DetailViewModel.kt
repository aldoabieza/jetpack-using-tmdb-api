package com.something.subfirstjetpack.ui.detail

import androidx.lifecycle.*
import com.something.subfirstjetpack.data.source.remote.response.Movies
import com.something.subfirstjetpack.data.source.remote.response.TvShow
import com.something.subfirstjetpack.data.source.repository.MovieRepository

class DetailViewModel(private val repo: MovieRepository) : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvId: String

    fun selectedMovie(movieId: String){
        this.movieId = movieId
    }

    fun selectedTv(tvId: String){
        this.tvId = tvId
    }

    fun getDetailMovies(): LiveData<Movies> = repo.getAllDetailMovies(movieId)
    fun getDetailTvShow() : LiveData<TvShow> = repo.getAllDetailTvShow(tvId)
}