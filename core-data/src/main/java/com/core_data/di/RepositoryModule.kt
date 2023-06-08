package com.core_data.di

import com.core_data.LoginRepositoryImpl
import com.core_data.UserRepositoryImpl
import com.core_domain.repository.LoginRepository
import com.core_domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository

    @Binds
    fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository

}