package com.something.subfirstjetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.something.subfirstjetpack.data.source.repository.MovieRepository
import com.something.subfirstjetpack.network.ApiConfig
import com.something.subfirstjetpack.ui.detail.DetailViewModel
import com.something.subfirstjetpack.ui.movie.MovieViewModel
import com.something.subfirstjetpack.ui.tvshow.TvShowViewModel
import com.something.subfirstjetpack.di.Injection

class ViewModelFactory(private val repository: MovieRepository): ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(api: ApiConfig): ViewModelFactory =
                instance ?: synchronized(this){
                    instance ?: ViewModelFactory(Injection.provideRepository(api))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(repository) as T
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> TvShowViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository) as T
            else -> throw Throwable("ViewModel Not Found : " + modelClass.name)
        }
    }
}