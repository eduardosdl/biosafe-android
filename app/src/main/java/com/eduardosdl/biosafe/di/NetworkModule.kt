package com.eduardosdl.biosafe.di

import com.eduardosdl.biosafe.data.api.ApiRequest
import com.eduardosdl.biosafe.data.api.HttpClientProvider
import com.eduardosdl.biosafe.data.api.JsonProvider
import org.koin.dsl.module

val networkModule = module {
    single { JsonProvider().instance }

    single { HttpClientProvider(get()).instance }

    single { ApiRequest(get()) }
}