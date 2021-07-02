package com.indaco.daggertestapp.core.di.modules

import android.app.Application
import com.indaco.daggertestapp.App
import com.indaco.daggertestapp.core.di.DebugAllOpen
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@DebugAllOpen
@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Application = app
}