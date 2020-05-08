package com.anggit97.hackernews.ui.topstorydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anggit97.hackernews.data.Comment
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
interface TopStoryDetailViewModelContract{

    fun getComment(commentId: String)
}

class TopStoryDetailViewModel @Inject constructor(
    private val useCase: HackerNewsUseCase,
    dispatcher: SchedulerProvider
) : BaseViewModel(dispatcher), TopStoryDetailViewModelContract{

    /**
     * Comment Detail
     */
    private val _items = MutableLiveData<Comment>()
    val items: LiveData<Comment>
    get() = _items

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
     * Get Comment By Comment Id
     */
    override fun getComment(commentId: String) {
        _state.value = LoaderState.ShowLoading
        launch {
            val result = useCase.getComment(commentId)
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