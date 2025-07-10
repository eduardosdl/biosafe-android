package com.eduardosdl.biosafe.di

import com.eduardosdl.biosafe.presentation.features.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}