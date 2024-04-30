package com.edominguez.moviedb.main.viewmodel

import androidx.lifecycle.ViewModel
import com.edominguez.moviedb.main.usecase.MainUseCase

class MainViewModel(
    private val mainUseCase: MainUseCase,
    val mainVMDelegate: MainVMDelegate
) : ViewModel() {

}