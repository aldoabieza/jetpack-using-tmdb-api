package com.something.subfirstjetpack.data.source.remote.datasource

import androidx.lifecycle.LiveData
import com.something.subfirstjetpack.data.source.remote.response.Movies
import com.something.subfirstjetpack.data.source.remote.response.TvShow

interface MovieDataSource {
    fun getAllMovies(): LiveData<List<Movies>>
    fun getAllDetailMovies(movieId: String): LiveData<Movies>
    fun getAllTvShow(): LiveData<List<TvShow>>
    fun getAllDetailTvShow(tvId: String): LiveData<TvShow>
}