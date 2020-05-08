package com.anggit97.hackernews.di

import androidx.lifecycle.ViewModel
import com.anggit97.hackernews.ui.topstorieslist.TopStoriesListViewModel
import com.anggit97.hackernews.ui.topstorydetail.TopStoryDetailViewModel
import com.anggit97.hackernews.utils.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
@Module
abstract class AppViewModel {

    @Binds
    @IntoMap
    @ViewModelKey(TopStoriesListViewModel::class)
    abstract fun bindTopStoriesListViewModel(viewModel: TopStoriesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopStoryDetailViewModel::class)
    abstract fun bindTopStoryDetailViewModel(viewModel: TopStoryDetailViewModel): ViewModel
}