package com.amlavati.delabresearchtakehomeassignment.di

import com.amlavati.delabresearchtakehomeassignment.App
import com.amlavati.delabresearchtakehomeassignment.viewmodel.CounterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        App()
    }

    viewModel {
        CounterViewModel(get())
    }
}