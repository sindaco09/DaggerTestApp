package com.indaco.daggertestapp.data.network

import com.indaco.daggertestapp.core.hilt.modules.network.components.OkHttpModule
import com.indaco.daggertestapp.hilt.MockInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [OkHttpModule::class]
)
class TestOkHttpModule {

    @Provides
    @Singleton
    fun provideMockInterceptor(): MockInterceptor = MockInterceptor()

    @Provides
    fun provideOkHttpClient(mockInterceptor: MockInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(mockInterceptor)

        return builder.build()
    }
}