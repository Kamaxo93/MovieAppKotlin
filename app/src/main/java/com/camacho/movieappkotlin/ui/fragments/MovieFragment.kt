package com.camacho.movieappkotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.camacho.movieappkotlin.R
import com.camacho.movieappkotlin.core.Resource
import com.camacho.movieappkotlin.data.remote.MovieDataSource
import com.camacho.movieappkotlin.databinding.FragmentMovieBinding
import com.camacho.movieappkotlin.presentation.MovieViewModel
import com.camacho.movieappkotlin.presentation.MovieViewModelFactory
import com.camacho.movieappkotlin.repository.MovieRepositoryImpl
import com.camacho.movieappkotlin.repository.RetrofitClient

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding
   private val viewModel by viewModels<MovieViewModel> { MovieViewModelFactory(MovieRepositoryImpl(
       MovieDataSource(RetrofitClient.webService)
   )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveData", "${it.data}")
                }
                is Resource.Failure -> {
                    Log.e("Error", "${it.exception}")
                }
            }
        })
    }
}