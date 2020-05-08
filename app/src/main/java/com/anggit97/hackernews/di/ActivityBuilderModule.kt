package com.anggit97.hackernews.di

import com.anggit97.hackernews.ui.topstorieslist.TopStoriesListActivity
import com.anggit97.hackernews.ui.topstorydetail.TopStoryDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [AppViewModel::class]
    )
    abstract fun contributeTopStoriesListActivity(): TopStoriesListActivity

    @ContributesAndroidInjector(
        modules = []
    )
    abstract fun contributeTopStoryDetailActivity(): TopStoryDetailActivity
}