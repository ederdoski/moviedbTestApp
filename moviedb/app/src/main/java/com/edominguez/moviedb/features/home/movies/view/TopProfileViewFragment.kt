package com.edominguez.moviedb.features.home.movies.view

import com.edominguez.moviedb.core.base.BaseFragment
import com.edominguez.moviedb.core.extensions.observe
import com.edominguez.moviedb.databinding.HomeViewFragmentBinding
import com.edominguez.moviedb.features.home.movies.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopProfileViewFragment : BaseFragment<HomeViewFragmentBinding>() {

    private val homeViewModel: HomeViewModel by viewModel()

    // ---- Base functions

    override fun screenName() = "TopProfileViewFragment"

    override fun listenToObserver() {
        observe(homeViewModel.homeVMDelegate.showUnknownError, this::onError)
    }

    // ---- Initialize your view here

    override fun init() {
        setOnClickListeners()
        homeViewModel.getMorePopularUser()
    }

    // ----- Logic Methods

    private fun setOnClickListeners() {

    }


    // ----- UI Methods


}
