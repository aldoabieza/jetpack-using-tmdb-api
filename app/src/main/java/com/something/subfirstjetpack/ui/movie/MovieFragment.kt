package com.something.subfirstjetpack.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.something.subfirstjetpack.network.ApiConfig
import com.something.subfirstjetpack.databinding.FragmentMovieBinding
import com.something.subfirstjetpack.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private val factory = ViewModelFactory.getInstance(ApiConfig)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View{
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null){
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val adapter = AdapterMovie()
            viewModel.getMovies().observe(this, { listMovie ->
                fragmentMovieBinding.pbMovie.visibility = View.GONE
                fragmentMovieBinding.rvMovie.visibility = View.VISIBLE
                adapter.setMovie(listMovie)
                adapter.notifyDataSetChanged()
            })

            fragmentMovieBinding.rvMovie.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}