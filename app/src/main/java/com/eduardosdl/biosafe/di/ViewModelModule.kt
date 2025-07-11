package com.eduardosdl.biosafe.di

import com.eduardosdl.biosafe.presentation.features.users.UsersViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UsersViewModel(get()) }
}