package com.anggit97.hackernews.ui.topstorieslist

import android.os.Bundle
import android.util.Log.e
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.anggit97.hackernews.R
import com.anggit97.hackernews.base.BaseActivity
import com.anggit97.hackernews.utils.ext.setGone
import com.anggit97.hackernews.utils.ext.setVisible
import com.anggit97.hackernews.utils.state.LoaderState
import kotlinx.android.synthetic.main.activity_top_stories_list.*
import javax.inject.Inject

class TopStoriesListActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: TopStoriesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_stories_list)
        initViewModel()
        observeViewModel()
        fetchData()
    }

    private fun fetchData() {
        viewModel.getTopStories()
    }

    private fun observeViewModel() {
        viewModel.ids.observe(this, Observer {
            it?.let {
                handleIdsResponse(it)
            }
        })

        viewModel.state.observe(this, Observer {
            it?.let {
                handleLoadingState(it)
            }
        })
    }

    private fun handleLoadingState(loading: LoaderState) {
        if (loading is LoaderState.ShowLoading){
            progressBarTop.setVisible()
        }else{
            progressBarTop.setGone()
        }
    }

    private fun handleIdsResponse(data: List<Int>) {
        e("DATA", data.toString())
    }

    @Suppress("DEPRECATION")
    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[TopStoriesListViewModel::class.java]
    }
}
