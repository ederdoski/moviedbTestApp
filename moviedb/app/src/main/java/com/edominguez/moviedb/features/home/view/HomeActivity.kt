package com.edominguez.moviedb.features.home.view

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
        const val BOTTOM_MENU_ITEM_CREDITO = 2
        const val BOTTOM_MENU_ITEM_PUNTOS = 3
        const val BOTTOM_MENU_ITEM_PERFIL = 4
    }

    override fun init() {
        Functions.setStatusBarColor(this, R.color.blue_dark)
    }

    override fun onFragmentEvent(action: ProtocolAction) {
        when (action) {
            is ProtocolAction.OnSelectedHomeItem -> setSelectedMenuItem(action.option)
            else -> {}
        }
    }

    //---- Logic Methods

    private fun setSelectedMenuItem(item: Int) {
        disableMenuItem(binding.menu.imgHome, binding.menu.tvItemHome)
        disableMenuItem(binding.menu.imgMaps, binding.menu.tvItemMaps)
        disableMenuItem(binding.menu.imgUpload, binding.menu.tvItemUpload)
        disableMenuItem(binding.menu.imgProfile, binding.menu.tvItemProfile)
        when (item) {
            BOTTOM_MENU_ITEM_HOME -> enableMenuItem(
                binding.menu.imgHome,
                binding.menu.tvItemHome
            )

            BOTTOM_MENU_ITEM_CREDITO -> enableMenuItem(
                binding.menu.imgMaps,
                binding.menu.tvItemMaps
            )

            BOTTOM_MENU_ITEM_PUNTOS -> enableMenuItem(
                binding.menu.imgUpload,
                binding.menu.tvItemUpload
            )

            BOTTOM_MENU_ITEM_PERFIL -> enableMenuItem(
                binding.menu.imgProfile,
                binding.menu.tvItemProfile
            )
        }
    }

    //---- UI Methods

    private fun disableMenuItem(icon: ImageView, text: TextView) {
        icon.setColorFilter(
            ContextCompat.getColor(this, R.color.gray),
            android.graphics.PorterDuff.Mode.SRC_ATOP
        )
        text.setTextColor(ContextCompat.getColor(this, R.color.gray))
    }

    private fun enableMenuItem(icon: ImageView, text: TextView) {
        icon.setColorFilter(
            ContextCompat.getColor(this, R.color.white),
            android.graphics.PorterDuff.Mode.SRC_ATOP
        )
        text.setTextColor(ContextCompat.getColor(this, R.color.white))
    }

}