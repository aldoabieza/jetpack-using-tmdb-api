package com.something.subfirstjetpack.di

import com.something.subfirstjetpack.data.source.remote.datasource.RemoteDataSource
import com.something.subfirstjetpack.data.source.repository.MovieRepository
import com.something.subfirstjetpack.network.ApiConfig

object Injection {
    fun provideRepository(api: ApiConfig): MovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance(api)
        return MovieRepository.getInstance(remoteDataSource)
    }
}