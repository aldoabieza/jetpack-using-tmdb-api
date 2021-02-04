package com.something.subfirstjetpack.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.something.subfirstjetpack.data.source.remote.response.TvShow
import com.something.subfirstjetpack.data.source.repository.MovieRepository

class TvShowViewModel(private val repo: MovieRepository) : ViewModel() {
    fun getTvShow(): LiveData<List<TvShow>> = repo.getAllTvShow()
}