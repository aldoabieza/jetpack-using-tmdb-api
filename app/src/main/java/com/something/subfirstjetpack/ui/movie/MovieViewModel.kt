package com.something.subfirstjetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.something.subfirstjetpack.data.source.remote.response.Movies
import com.something.subfirstjetpack.data.source.repository.MovieRepository

class MovieViewModel(private val repo: MovieRepository) : ViewModel() {
    fun getMovies(): LiveData<List<Movies>> = repo.getAllMovies()
}