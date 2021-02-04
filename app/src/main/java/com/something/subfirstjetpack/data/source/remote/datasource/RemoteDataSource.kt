package com.something.subfirstjetpack.data.source.remote.datasource

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.something.subfirstjetpack.BuildConfig
import com.something.subfirstjetpack.network.ApiConfig
import com.something.subfirstjetpack.data.source.remote.response.MovieResponse
import com.something.subfirstjetpack.data.source.remote.response.Movies
import com.something.subfirstjetpack.data.source.remote.response.TvShow
import com.something.subfirstjetpack.data.source.remote.response.TvShowResponse
import com.something.subfirstjetpack.util.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val config: ApiConfig){

    private val apiKey = BuildConfig.MovieToken
    private var handler = Handler(Looper.getMainLooper())
    companion object{
        private const val TIME_LIMIT: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(api: ApiConfig): RemoteDataSource =
                instance ?: synchronized(this){
                    instance ?: RemoteDataSource(api)
                }
    }

    fun getMovies(callback: LoadListMovie) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            config.getApiService().getMovies(apiKey)
                    .enqueue(object : Callback<MovieResponse>{
                        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                            if (response.isSuccessful){
                                response.body()?.results?.let { callback.onAllListMovie(it) }
                                EspressoIdlingResource.decrement()
                            }
                        }

                        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                            Log.d("OnFailure", t.message.toString())
                        }
                    })
        }, TIME_LIMIT)
    }

    fun getDetailMovies(movieId: String, callback: LoadDetailMovies){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            config.getApiService().getDetailMovies(movieId, apiKey)
                    .enqueue(object: Callback<Movies>{
                        override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                            if (response.isSuccessful){
                                response.body()?.let { callback.onAllDetailMovie(it) }
                                EspressoIdlingResource.decrement()
                            }
                        }
                        override fun onFailure(call: Call<Movies>, t: Throwable) {
                            Log.d("OnFailure", t.message.toString())
                        }
                    })
        }, TIME_LIMIT)
    }

    fun getTvShow(callback: LoadListTvShow){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            config.getApiService().getTvShows(apiKey)
                    .enqueue(object : Callback<TvShowResponse>{
                        override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                            if (response.isSuccessful){
                                callback.onAllListTv(response.body()?.results as List<TvShow>)
                                EspressoIdlingResource.decrement()
                            }
                        }

                        override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                            Log.d("OnFailure", t.message.toString())
                        }

                    })
        }, TIME_LIMIT)
    }

    fun getDetailTvShow(tvId: String, callback: LoadDetailTvShow){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            config.getApiService().getTvShowDetail(tvId, apiKey)
                    .enqueue(object: Callback<TvShow>{
                        override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                            if (response.isSuccessful) response.body()?.let { callback.onAllDetailTv(it) }
                            EspressoIdlingResource.decrement()
                        }
                        override fun onFailure(call: Call<TvShow>, t: Throwable) {
                            Log.d("OnFailure", t.message.toString())
                        }
                    })
        }, TIME_LIMIT)
    }

    interface LoadListMovie {
        fun onAllListMovie(movieResponse: List<Movies>)
    }

    interface LoadDetailMovies {
        fun onAllDetailMovie(movieResponse: Movies)
    }

    interface LoadListTvShow {
        fun onAllListTv(tvResponse: List<TvShow>)
    }

    interface LoadDetailTvShow {
        fun onAllDetailTv(tvResponse: TvShow)
    }

}