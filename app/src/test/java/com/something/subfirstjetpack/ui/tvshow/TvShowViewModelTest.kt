package com.something.subfirstjetpack.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.something.subfirstjetpack.data.source.remote.response.TvShow
import com.something.subfirstjetpack.data.source.repository.MovieRepository
import com.something.subfirstjetpack.util.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<TvShow>>

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShow() {

        val dummyTvShow = DataDummy.generateTvShow()
        val tvShow = MutableLiveData<List<TvShow>>()
        tvShow.value = dummyTvShow

        `when`(movieRepository.getAllTvShow()).thenReturn(tvShow)
        val tvEntities = viewModel.getTvShow().value
        verify(movieRepository).getAllTvShow()
        assertNotNull(tvEntities)
        assertEquals(20, tvEntities?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)

    }
}