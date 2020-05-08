package com.anggit97.hackernews.ui.topstorieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anggit97.hackernews.data.TopStoryDetail
import com.anggit97.hackernews.domain.HackerNewsUseCase
import com.anggit97.hackernews.utils.state.LoaderState
import com.anggit97.hackernews.utils.state.ResultState
import com.anggit97.hackernews.utils.thread.SchedulerProvider
import com.anggit97.hackernews.utils.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Anggit Prayogo on 5/8/20.
 */

interface TopStoriesListViewModelContract{

    fun getTopStories()

    fun getTopStoryDetail(storyId: String)
}

class TopStoriesListViewModel @Inject constructor(
    private val useCase: HackerNewsUseCase,
    dispatcher: SchedulerProvider
): BaseViewModel(dispatcher), TopStoriesListViewModelContract{

    private val _items = MutableLiveData<TopStoryDetail>()
    val items : LiveData<TopStoryDetail>
    get() = _items

    private val _ids = MutableLiveData<List<Int>>()
    val ids : LiveData<List<Int>>
    get() = _ids

    /**
     * error
     */
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    /**
     * Network State
     */
    private val _networkError = MutableLiveData<Boolean>()
    val networkError: LiveData<Boolean>
        get() = _networkError

    /**
     * Loading
     */
    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state


    /**
     * Get Top Stories
     */
    override fun getTopStories() {
        _state.value = LoaderState.ShowLoading
        launch {
            val result = useCase.getTopStories()
            withContext(Dispatchers.Main){
                _state.value = LoaderState.HideLoading
                when(result){
                    is ResultState.Success -> _ids.value = result.data
                    is ResultState.GeneralError -> _error.value = result.error
                    else -> _networkError.value = true
                }
            }
        }
    }

    /**
     * Get Story Detail
     */
    override fun getTopStoryDetail(storyId: String) {
        _state.value = LoaderState.ShowLoading
        launch {
            val result = useCase.getTopStoryDetail(storyId)
            withContext(Dispatchers.Main){
                _state.value = LoaderState.HideLoading
                when(result){
                    is ResultState.Success -> _items.value = result.data
                    is ResultState.GeneralError -> _error.value = result.error
                    else -> _networkError.value = true
                }
            }
        }
    }
}