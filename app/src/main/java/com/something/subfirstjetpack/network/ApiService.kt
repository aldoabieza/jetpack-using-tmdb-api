package com.something.subfirstjetpack.network

import com.something.subfirstjetpack.data.source.remote.response.MovieResponse
import com.something.subfirstjetpack.data.source.remote.response.Movies
import com.something.subfirstjetpack.data.source.remote.response.TvShow
import com.something.subfirstjetpack.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey: String): Call<MovieResponse>
    
    @GET("movie/{movie_id}")
    fun getDetailMovies(
            @Path("movie_id") movieId: String, @Query("api_key") apiKey: String) : Call<Movies>

    @GET("tv/popular")
    fun getTvShows(@Query("api_key") apiKey: String) : Call<TvShowResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
            @Path("tv_id") tvId: String, @Query("api_key") apiKey: String): Call<TvShow>
        
}