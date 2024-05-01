package com.edominguez.moviedb.main.view

import android.content.Intent
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDelegate
import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.base.BaseActivity
import com.edominguez.moviedb.core.common.utils.TWO_SECONDS
import com.edominguez.moviedb.core.protocol.ProtocolAction
import com.edominguez.moviedb.databinding.MainViewActivityBinding
import com.edominguez.moviedb.features.home.movies.view.HomeActivity
import com.edominguez.moviedb.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainViewActivity : BaseActivity<MainViewActivityBinding>() {

    fun listenToObserver() {}

    override fun onFragmentEvent(action: ProtocolAction) {}

    //---- Initialize your view here

    override fun init() {
        delayScreen()
        listenToObserver()
        animationListener()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    //---- Navigation Methods

    private fun goHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    //---- Logic Methods

    private fun delayScreen(){
        android.os.Handler().postDelayed({
            goHome()
        }, TWO_SECONDS)
    }

    //---- UI Methods

    private fun animationListener() {
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation)
        binding.imgSplash.startAnimation(fadeInAnimation)
    }

}