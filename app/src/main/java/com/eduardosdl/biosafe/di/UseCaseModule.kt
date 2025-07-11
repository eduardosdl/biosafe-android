package com.eduardosdl.biosafe.di

import com.eduardosdl.biosafe.domain.usecase.UserUseCase
import org.koin.dsl.module

val UseCaseModule = module {
    single { UserUseCase(get()) }
}