package com.edominguez.moviedb.features.home.movies.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.base.BaseFragment
import com.edominguez.moviedb.core.base.IOnItemClickViewHolder
import com.edominguez.moviedb.core.common.utils.GRID_QUANTITY
import com.edominguez.moviedb.core.common.utils.MOVIE_DB_BIG_IMG_API
import com.edominguez.moviedb.core.common.utils.MapsDelegate
import com.edominguez.moviedb.core.common.utils.QUANTITY_MINUTES_TO_REFRESH_MOVIES
import com.edominguez.moviedb.core.common.utils.QUANTITY_MINUTES_TO_REFRESH_POSITION
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
import com.google.android.gms.maps.model.LatLng
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
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
        initAdapter()
        setOnClickListeners()
        updateCatalogOfMovies()
        homeViewModel.getMovies(MOVIE_FILTER_POPULAR)
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
        setRecyclerListener()
    }

    private fun setRecyclerListener() {
        bindingView.movies.rvMovies.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!bindingView.movies.rvMovies.canScrollVertically(1)) {
                    homeViewModel.getMovies(MOVIE_FILTER_POPULAR)
                }
            }
        })
    }

    private fun initAdapter() {
        val tmpAdapter = MoviesAdapter(onItemClickListener = moviesClickListener)
        val gridLayoutManager = GridLayoutManager(requireContext(), GRID_QUANTITY)
        bindingView.movies.rvMovies.layoutManager = gridLayoutManager
        bindingView.movies.rvMovies.adapter = tmpAdapter
    }

    private fun setListOfMovies(aMovies: ArrayList<MovieData>) {
        val tmpAdapter = bindingView.movies.rvMovies.adapter as MoviesAdapter
        tmpAdapter.listData = aMovies
    }

    private fun onListMovieResponse(moviesResponseData: MoviesResponseData) {
        val aMovies = moviesResponseData.results
        setListOfMovies(aMovies)
        setTopMovie(aMovies[Random.nextInt(aMovies.size)])
    }

    private val moviesClickListener = object : IOnItemClickViewHolder {
        override fun onItemClick(caller: View?, position: Int) {}
    }

    private fun updateCatalogOfMovies() {
        val scheduler = Executors.newScheduledThreadPool(1)

        scheduler.scheduleAtFixedRate({
            homeViewModel.getMovies(MOVIE_FILTER_POPULAR)
        }, 0, QUANTITY_MINUTES_TO_REFRESH_MOVIES, TimeUnit.SECONDS)
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
