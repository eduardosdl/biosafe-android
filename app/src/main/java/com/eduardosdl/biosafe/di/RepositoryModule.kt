package com.eduardosdl.biosafe.di

import com.eduardosdl.biosafe.data.repository.UserRepositoryImpl
import com.eduardosdl.biosafe.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}