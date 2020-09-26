package com.adedom.websocketandroid.presentation.di

import com.adedom.websocketandroid.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val presentationModule = module {

    viewModel { MainViewModel(get(), get()) }

}

val getPresentationModule = presentationModule
