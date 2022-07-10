package com.example.android.hilt.di

import com.example.android.hilt.data.LoggerDataSource
import com.example.android.hilt.data.LoggerLocalDataSource
import com.example.android.hilt.repo.LogRepository
import com.example.android.hilt.repo.LogRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepoModule {
    @Binds
    abstract fun bindRepository(impl : LogRepositoryImpl) : LogRepository
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class LocalModule {
    @Binds
    abstract fun bindLocal(impl : LoggerLocalDataSource) : LoggerDataSource
}