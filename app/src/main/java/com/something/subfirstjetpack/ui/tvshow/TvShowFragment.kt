package com.something.subfirstjetpack.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.something.subfirstjetpack.network.ApiConfig
import com.something.subfirstjetpack.databinding.FragmentTvShowBinding
import com.something.subfirstjetpack.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var binding : FragmentTvShowBinding
    private val factory = ViewModelFactory.getInstance(ApiConfig)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
        val adapter = AdapterTvShow()
        if(activity != null){
            viewModel.getTvShow().observe(this, { listItem ->
                binding.rvTvshow.visibility = View.VISIBLE
                binding.pbTvshow.visibility = View.GONE
                adapter.setMovie(listItem)
                adapter.notifyDataSetChanged()
            })
            with(binding.rvTvshow){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}