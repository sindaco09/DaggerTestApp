package com.indaco.daggertestapp.core.di.modules

import com.indaco.daggertestapp.core.di.DebugAllOpen
import com.indaco.daggertestapp.data.repositories.UserRepository
import com.indaco.daggertestapp.data.storage.cache.UserCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@DebugAllOpen
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepo(userCache: UserCache): UserRepository = UserRepository(userCache)

}