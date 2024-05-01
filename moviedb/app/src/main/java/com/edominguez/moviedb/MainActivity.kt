package com.edominguez.moviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.edominguez.moviedb.features.home.movies.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.setSession()
    }
}
