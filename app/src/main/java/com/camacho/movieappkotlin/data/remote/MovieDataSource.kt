package com.camacho.movieappkotlin.data.remote

import com.camacho.movieappkotlin.application.AppConstant
import com.camacho.movieappkotlin.data.model.MovieList
import com.camacho.movieappkotlin.repository.WebService

class MovieDataSource(private val service: WebService) {

    suspend fun getUpcomingMovies(): MovieList = service.getUpcomingMovies(AppConstant.API_KEY)

    suspend fun getTopRatedMovies(): MovieList = service.getTopRatedMovies(AppConstant.API_KEY)

    suspend fun getPopularMovies(): MovieList = service.getPopularMovies(AppConstant.API_KEY)
}