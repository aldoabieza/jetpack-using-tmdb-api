package com.something.subfirstjetpack.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.something.subfirstjetpack.data.source.remote.response.Movies
import com.something.subfirstjetpack.data.source.remote.response.TvShow
import com.something.subfirstjetpack.data.source.repository.FakeMovieRepository
import com.something.subfirstjetpack.data.source.repository.MovieRepository
import com.something.subfirstjetpack.util.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovies = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovies.id.toString()
    private val dummyTv = DataDummy.generateTvShow()[0]
    private val tvId = dummyTv.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<Movies>

    @Mock
    private lateinit var tvObserver: Observer<TvShow>

    @Before
    fun setUp(){
        viewModel = DetailViewModel(movieRepository)
        viewModel.selectedMovie(movieId)
        viewModel.selectedTv(tvId)
    }

    @Test
    fun getDetailMovies() {
        val movies = MutableLiveData<Movies>()
        movies.value = dummyMovies
        `when`(movieRepository.getAllDetailMovies(movieId)).thenReturn(movies)
        val moviesResponse = viewModel.getDetailMovies().value as Movies
        verify(movieRepository).getAllDetailMovies(movieId)
        assertNotNull(moviesResponse)
        assertEquals(dummyMovies.id, moviesResponse.id)
        assertEquals(dummyMovies.voteAverage, moviesResponse.voteAverage)
        assertEquals(dummyMovies.name, moviesResponse.name)
        assertEquals(dummyMovies.firstAirDate, moviesResponse.firstAirDate)
        assertEquals(dummyMovies.overview, moviesResponse.overview)
        assertEquals(dummyMovies.posterPath, moviesResponse.posterPath)


        viewModel.getDetailMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovies)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<TvShow>()
        tvShow.value = dummyTv
        `when`(movieRepository.getAllDetailTvShow(tvId)).thenReturn(tvShow)
        val tvResponse = viewModel.getDetailTvShow().value as TvShow
        verify(movieRepository).getAllDetailTvShow(tvId)
        assertNotNull(tvResponse)
        assertEquals(dummyTv.id, tvResponse.id)
        assertEquals(dummyTv.name, tvResponse.name)
        assertEquals(dummyTv.posterPath, tvResponse.posterPath)
        assertEquals(dummyTv.firstAirDate, tvResponse.firstAirDate)
        assertEquals(dummyTv.overview, tvResponse.overview)
        assertEquals(dummyTv.voteAverage, tvResponse.voteAverage)

        viewModel.getDetailTvShow().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTv)

    }
}