package com.something.subfirstjetpack.data.source.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.something.subfirstjetpack.data.source.remote.datasource.RemoteDataSource
import com.something.subfirstjetpack.util.DataDummy
import com.something.subfirstjetpack.helper.LiveDataTestUtil
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val fakeRepository = FakeMovieRepository(remote)

    private val movieLocalResponse = DataDummy.generateDummyMovies()
    private val movieId = movieLocalResponse[0].id.toString()
    private val tvLocalResponse = DataDummy.generateTvShow()
    private val tvId = tvLocalResponse[0].id.toString()

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadListMovie)
                .onAllListMovie(movieLocalResponse)
            null
        }.`when`(remote).getMovies(any())
        val listMovie = LiveDataTestUtil.getValue(fakeRepository.getAllMovies())
        assertNotNull(listMovie)
        assertEquals(movieLocalResponse.size, listMovie.size)
    }

    @Test
    fun getAllTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadListTvShow)
                    .onAllListTv(tvLocalResponse)
            null
        }.`when`(remote).getTvShow(any())
        val dataTv = LiveDataTestUtil.getValue(fakeRepository.getAllTvShow())
        assertNotNull(dataTv)
        assertEquals(tvLocalResponse.size, dataTv.size)
    }

    @Test
    fun getAllDetailMovies() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailMovies)
                    .onAllDetailMovie(movieLocalResponse[0])
            null
        }.`when`(remote).getDetailMovies(eq(movieId), any())
        val dataMovie = LiveDataTestUtil.getValue(fakeRepository.getAllDetailMovies(movieId))
        assertNotNull(dataMovie)
    }

    @Test
    fun getAllDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailTvShow)
                    .onAllDetailTv(tvLocalResponse[0])
            null
        }.`when`(remote).getDetailTvShow(eq(tvId), any())
        val detailTv = LiveDataTestUtil.getValue(fakeRepository.getAllDetailTvShow(tvId))
        assertNotNull(detailTv)
    }
}