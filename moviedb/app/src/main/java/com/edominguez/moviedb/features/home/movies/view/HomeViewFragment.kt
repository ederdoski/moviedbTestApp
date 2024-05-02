package com.edominguez.moviedb.features.home.movies.view

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
import com.edominguez.moviedb.core.extensions.toVisible
import com.edominguez.moviedb.core.protocol.ProtocolAction
import com.edominguez.moviedb.databinding.HomeViewFragmentBinding
import com.edominguez.moviedb.features.home.movies.datasource.model.MovieData
import com.edominguez.moviedb.features.home.movies.datasource.model.MoviesResponseData
import com.edominguez.moviedb.features.home.movies.view.HomeActivity.Companion.MOVIE_FILTER_POPULAR
import com.edominguez.moviedb.features.home.movies.view.HomeActivity.Companion.MOVIE_FILTER_RECOMMENDED
import com.edominguez.moviedb.features.home.movies.view.HomeActivity.Companion.MOVIE_FILTER_TOP_RATED
import com.edominguez.moviedb.features.home.movies.view.cell.MoviesAdapter
import com.edominguez.moviedb.features.home.movies.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class HomeViewFragment : BaseFragment<HomeViewFragmentBinding>() {

    private val homeViewModel: HomeViewModel by viewModel()

    // ---- Base functions

    override fun screenName() = "HomeViewFragment"

    override fun listenToObserver() {
        observe(homeViewModel.homeVMDelegate.showUnknownError, this::onError)
        observe(homeViewModel.homeVMDelegate.onListMovieResponse, this::onListMovieResponse)
    }

    // ---- Initialize your view here

    override fun init() {
        bindUI()
        setOnClickListeners()
      //  homeViewModel.getMovies(MOVIE_FILTER_POPULAR)
    }

    // ----- Logic Methods

    private fun setOnClickListeners() {
        bindingView.filters.txtFilterMorePopulars.setOnClickListener {
            homeViewModel.getMovies(MOVIE_FILTER_POPULAR)
            changeColorOfFilter(bindingView.filters.txtFilterMorePopulars)
        }

        bindingView.filters.txtFilterRecommendations.setOnClickListener {
            homeViewModel.getMovies(MOVIE_FILTER_RECOMMENDED)
            changeColorOfFilter(bindingView.filters.txtFilterRecommendations)
        }

        bindingView.filters.txtFilterBestRating.setOnClickListener {
            homeViewModel.getMovies(MOVIE_FILTER_TOP_RATED)
            changeColorOfFilter(bindingView.filters.txtFilterBestRating)
        }
    }

    private fun onListMovieResponse(moviesResponseData: MoviesResponseData) {
        if (moviesResponseData.results.isNotEmpty()) {

            val aMovies = moviesResponseData.results
            val gridLayoutManager = GridLayoutManager(requireContext(), GRID_QUANTITY)
            bindingView.movies.rvMovies.layoutManager = gridLayoutManager
            bindingView.movies.rvMovies.adapter = MoviesAdapter(
                dataList = aMovies,
                onItemClickListener = moviesClickListener
            )
            setTopMovie(aMovies[Random.nextInt(aMovies.size)])
            //if (aTransactions.size < MAX_TRANSACTIONS) bindingView.cardTransaction.lyMoreTransactions.toInvisible()
        } else {
            //showNoTransactionLayout()
        }
    }

    private val moviesClickListener = object : IOnItemClickViewHolder {
        override fun onItemClick(caller: View?, position: Int) {}
    }

    // ----- UI Methods

    private fun bindUI() {
        changeColorOfFilter(bindingView.filters.txtFilterMorePopulars)
        communication.onFragmentEvent(ProtocolAction.OnSelectedMenuItem(HomeActivity.BOTTOM_MENU_ITEM_HOME))
    }

    private fun setTopMovie(movieData: MovieData) {
        bindingView.topMovie.lyTopMovie.toVisible()
        bindingView.topMovie.imgTopMovie.setImageSrcFromUrl(MOVIE_DB_BIG_IMG_API + movieData.posterPath, R.drawable.img_moviedb, requireContext())
    }

    private fun changeColorOfFilter(textview:TextView) {
        resetColorOfFilter()
        textview.setBackgroundResource(R.drawable.bg_category_background_border)
    }

    private fun resetColorOfFilter() {
        val aTextview = arrayListOf(bindingView.filters.txtFilterMorePopulars, bindingView.filters.txtFilterRecommendations, bindingView.filters.txtFilterBestRating)
        aTextview.forEach {
            it.setBackgroundResource(R.drawable.bg_category_border)
        }
    }


}
