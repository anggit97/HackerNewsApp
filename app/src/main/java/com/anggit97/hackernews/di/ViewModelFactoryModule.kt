package com.anggit97.hackernews.di

import androidx.lifecycle.ViewModelProvider
import com.anggit97.hackernews.utils.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory
}