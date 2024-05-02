package com.edominguez.moviedb.features.home.movies.view

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.base.BaseActivity
import com.edominguez.moviedb.core.common.utils.Functions
import com.edominguez.moviedb.core.protocol.ProtocolAction
import com.edominguez.moviedb.databinding.HomeViewActivityBinding

class HomeActivity: BaseActivity<HomeViewActivityBinding>() {

    private val navController by lazy { binding.contentFragmentHome.findNavController() }

    companion object {
        const val MOVIE_FILTER_POPULAR = "now_playing"
        const val MOVIE_FILTER_TOP_RATED = "top_rated"
        const val MOVIE_FILTER_RECOMMENDED = "popular"

        const val BOTTOM_MENU_ITEM_HOME = 1
        const val BOTTOM_MENU_ITEM_MAPS = 2
        const val BOTTOM_MENU_ITEM_PROFILE = 4
        const val BOTTOM_MENU_ITEM_UPLOAD_REVIEWS = 3
    }

    override fun init() {
        setOnClickListeners()
        Functions.setStatusBarColor(this, R.color.blue_dark)
    }

    private fun setOnClickListeners() {
        binding.menu.lnItemHome.setOnClickListener {
            goToHome()
            checkInternetConnection()
        }
        binding.menu.lnItemMaps.setOnClickListener {
            goToMaps()
            checkInternetConnection()
        }
        binding.menu.lnItemProfile.setOnClickListener {
            goToProfile()
            checkInternetConnection()
        }
        binding.menu.lnItemUpload.setOnClickListener {
            goToUploadReview()
            checkInternetConnection()
        }
    }

    //---- Navigation Methods

    override fun onFragmentEvent(action: ProtocolAction) {
        when (action) {
            is ProtocolAction.OnSelectedMenuItem -> setSelectedMenuItem(action.option)
            is ProtocolAction.OnLoading -> loading(binding.loadingView.root, action.loading)
            else -> {}
        }
    }

    private fun goToHome() {
        goTo(navController, R.id.action_go_to_home)
    }

    private fun goToMaps() {
        goTo(navController, R.id.action_go_to_maps)
    }

    private fun goToProfile() {
        goTo(navController, R.id.action_go_to_profile)
    }

    private fun goToUploadReview() {
        goTo(navController, R.id.action_go_to_upload_reviews)
    }

    //---- Logic Methods

    private fun disableMenuItems() {
        val aIcons = arrayListOf(binding.menu.imgHome, binding.menu.imgMaps, binding.menu.imgUpload, binding.menu.imgProfile)
        val aTextView = arrayListOf(binding.menu.tvItemHome, binding.menu.tvItemMaps, binding.menu.tvItemUpload, binding.menu.tvItemProfile)

        aIcons.forEach {
            it.setColorFilter(
                ContextCompat.getColor(this, R.color.gray),
                android.graphics.PorterDuff.Mode.SRC_ATOP
            )
        }

        aTextView.forEach {
            it.setTextColor(ContextCompat.getColor(this, R.color.gray))
        }
    }

    private fun enableMenuItem(icon: ImageView, text: TextView) {
        icon.setColorFilter(
            ContextCompat.getColor(this, R.color.white),
            android.graphics.PorterDuff.Mode.SRC_ATOP
        )
        text.setTextColor(ContextCompat.getColor(this, R.color.white))
    }
    private fun setSelectedMenuItem(item: Int) {
        disableMenuItems()
        when (item) {
            BOTTOM_MENU_ITEM_HOME -> enableMenuItem(
                binding.menu.imgHome,
                binding.menu.tvItemHome
            )

            BOTTOM_MENU_ITEM_MAPS -> enableMenuItem(
                binding.menu.imgMaps,
                binding.menu.tvItemMaps
            )

            BOTTOM_MENU_ITEM_UPLOAD_REVIEWS -> enableMenuItem(
                binding.menu.imgUpload,
                binding.menu.tvItemUpload
            )

            BOTTOM_MENU_ITEM_PROFILE -> enableMenuItem(
                binding.menu.imgProfile,
                binding.menu.tvItemProfile
            )
        }
    }

    //---- UI Methods

    private fun checkInternetConnection() {
        if(!Functions.isInternetAvailable(this)) {
            networkError(binding.networkError.root, true)
        }else{
            networkError(binding.networkError.root, false)
        }
    }

}