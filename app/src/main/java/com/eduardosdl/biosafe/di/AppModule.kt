package com.eduardosdl.biosafe.di

import org.koin.dsl.module

val appModules = module {
    includes(
        networkModule,
        repositoryModule,
        viewModelModule
    )
}