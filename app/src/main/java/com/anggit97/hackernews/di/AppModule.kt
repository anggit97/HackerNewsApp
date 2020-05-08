package com.anggit97.hackernews.di

import android.app.Application
import android.content.Context
import com.anggit97.hackernews.BuildConfig
import com.anggit97.hackernews.data.repository.HackerNewsRepositoryImpl
import com.anggit97.hackernews.domain.HackerNewsRepository
import com.anggit97.hackernews.domain.HackerNewsUseCase
import com.anggit97.hackernews.network.HackerNewsApi
import com.anggit97.hackernews.utils.thread.AppSchedulerProvider
import com.anggit97.hackernews.utils.thread.SchedulerProvider
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(context: Application): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideRetrofit(context: Context): Retrofit {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(context.cacheDir, cacheSize)

        val okhttp = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .client(okhttp)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideHackerNewsService(retrofit: Retrofit): HackerNewsApi {
        return retrofit.create(HackerNewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHackerNewsRepository(apiService: HackerNewsApi) : HackerNewsRepository{
        return HackerNewsRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideHackerNewsUseCase(repository: HackerNewsRepository): HackerNewsUseCase{
        return HackerNewsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSchedulerCoroutine(): SchedulerProvider = AppSchedulerProvider()
}