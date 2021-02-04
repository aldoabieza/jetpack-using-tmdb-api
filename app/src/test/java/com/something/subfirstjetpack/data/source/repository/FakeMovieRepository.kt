package com.something.subfirstjetpack.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.something.subfirstjetpack.data.source.remote.datasource.MovieDataSource
import com.something.subfirstjetpack.data.source.remote.datasource.RemoteDataSource
import com.something.subfirstjetpack.data.source.remote.response.Movies
import com.something.subfirstjetpack.data.source.remote.response.TvShow

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource): MovieDataSource {

    override fun getAllMovies(): LiveData<List<Movies>> {
        val resultMovie = MutableLiveData<List<Movies>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadListMovie{
            override fun onAllListMovie(movieResponse: List<Movies>) {
                resultMovie.postValue(movieResponse)
            }
        })
        return resultMovie
    }

    override fun getAllDetailMovies(movieId: String): LiveData<Movies> {
        val resultMovie = MutableLiveData<Movies>()
        remoteDataSource.getDetailMovies(movieId, object : RemoteDataSource.LoadDetailMovies{
            override fun onAllDetailMovie(movieResponse: Movies) {
                resultMovie.postValue(movieResponse)
            }
        })
        return resultMovie
    }

    override fun getAllTvShow(): LiveData<List<TvShow>> {
        val resultTv = MutableLiveData<List<TvShow>>()
        remoteDataSource.getTvShow(object : RemoteDataSource.LoadListTvShow{
            override fun onAllListTv(tvResponse: List<TvShow>) {
                resultTv.postValue(tvResponse)
            }
        })
        return resultTv
    }

    override fun getAllDetailTvShow(tvId: String): LiveData<TvShow> {
       val resultTvShow = MutableLiveData<TvShow>()
       remoteDataSource.getDetailTvShow(tvId, object : RemoteDataSource.LoadDetailTvShow{
           override fun onAllDetailTv(tvResponse: TvShow) {
               resultTvShow.postValue(tvResponse)
           }
       })
       return resultTvShow
    }

}