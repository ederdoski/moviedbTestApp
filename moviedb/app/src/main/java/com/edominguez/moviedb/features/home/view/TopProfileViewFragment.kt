package com.edominguez.moviedb.features.home.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.base.BaseFragment
import com.edominguez.moviedb.core.base.IOnItemClickViewHolder
import com.edominguez.moviedb.core.common.utils.GRID_QUANTITY
import com.edominguez.moviedb.core.common.utils.MOVIE_DB_BIG_IMG_API
import com.edominguez.moviedb.core.extensions.observe
import com.edominguez.moviedb.core.extensions.setImageSrcFromUrl
import com.edominguez.moviedb.core.protocol.ProtocolAction
import com.edominguez.moviedb.databinding.HomeViewFragmentBinding
import com.edominguez.moviedb.features.home.datasource.model.MovieData
import com.edominguez.moviedb.features.home.datasource.model.MoviesResponseData
import com.edominguez.moviedb.features.home.view.cell.MoviesAdapter
import com.edominguez.moviedb.features.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class TopProfileViewFragment : BaseFragment<HomeViewFragmentBinding>() {

    private val homeViewModel: HomeViewModel by viewModel()

    // ---- Base functions

    override fun screenName() = "HomeViewFragment"

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
