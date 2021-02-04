package com.something.subfirstjetpack.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.something.subfirstjetpack.data.source.remote.response.Movies
import com.something.subfirstjetpack.data.source.repository.FakeMovieRepository
import com.something.subfirstjetpack.data.source.repository.MovieRepository
import com.something.subfirstjetpack.ui.detail.DetailViewModel
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<Movies>>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<Movies>>()
        movies.value = dummyMovies

        `when`(movieRepository.getAllMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        verify(movieRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}