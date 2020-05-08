package com.anggit97.hackernews.ui.topstorieslist

import com.anggit97.hackernews.domain.HackerNewsUseCase
import com.anggit97.hackernews.utils.thread.SchedulerProvider
import com.anggit97.hackernews.utils.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * Created by Anggit Prayogo on 5/8/20.
 */

interface TopStoriesListViewModelContract{

    fun getTopStories()
}

class TopStoriesListViewModel @Inject constructor(
    private val useCase: HackerNewsUseCase,
    dispatcher: SchedulerProvider
): BaseViewModel(dispatcher), TopStoriesListViewModelContract{

    override fun getTopStories() {

    }
}