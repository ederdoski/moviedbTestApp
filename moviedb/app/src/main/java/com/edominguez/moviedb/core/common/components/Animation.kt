package com.edominguez.moviedb.core.common.components

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.edominguez.moviedb.R
import com.edominguez.moviedb.core.extensions.toGone
import org.koin.core.component.KoinComponent

class Animation:KoinComponent {

    fun makeFadeAnimation(context:Context, textView:TextView, text:String) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in_animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                textView.text = text
            }

            override fun onAnimationEnd(animation: Animation) {
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        textView.startAnimation(animation)
    }

    fun hideAnimation(context: Context, view: View) {
        val shortAnimationDuration = context.resources.getInteger(android.R.integer.config_shortAnimTime)

        view.animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.toGone()
                }
            })
    }
}