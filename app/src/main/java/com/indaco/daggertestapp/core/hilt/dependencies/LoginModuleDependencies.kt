package com.indaco.daggertestapp.core.hilt.dependencies

import com.indaco.daggertestapp.data.storage.cache.UserSPCache
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LoginModuleDependencies {

    fun userSPCache(): UserSPCache
}