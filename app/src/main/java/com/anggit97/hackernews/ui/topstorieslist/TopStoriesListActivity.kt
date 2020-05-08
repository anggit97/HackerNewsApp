package com.anggit97.hackernews.ui.topstorieslist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.anggit97.hackernews.R
import com.anggit97.hackernews.base.BaseActivity
import com.anggit97.hackernews.data.TopStoryDetail
import com.anggit97.hackernews.ui.topstorydetail.TopStoryDetailActivity
import com.anggit97.hackernews.utils.ext.setGone
import com.anggit97.hackernews.utils.ext.setVisible
import com.anggit97.hackernews.utils.state.LoaderState
import kotlinx.android.synthetic.main.activity_top_stories_list.*
import javax.inject.Inject

class TopStoriesListActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: TopStoriesListViewModel

    private lateinit var adapter: TopStoriesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_stories_list)
        initViewModel()
        initRecyclerView()
        observeViewModel()
        fetchData()
    }

    private fun initRecyclerView() {
        adapter = TopStoriesListAdapter()
        adapter.setActivity(this)
        rvTopStories.layoutManager = GridLayoutManager(this, 2)
        rvTopStories.adapter = adapter
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

        viewModel.items.observe(this, Observer {
            it?.let {
                handleStoryDetailResponse(it)
            }
        })
    }

    private fun handleStoryDetailResponse(data: TopStoryDetail) {
        adapter.addItem(data)
    }

    private fun handleLoadingState(loading: LoaderState) {
        if (loading is LoaderState.ShowLoading){
            progressBarTop.setVisible()
        }else{
            progressBarTop.setGone()
        }
    }

    private fun handleIdsResponse(data: List<Int>) {
        val top20Story = data.take(50)

        top20Story.forEach {
            viewModel.getTopStoryDetail(it.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TopStoryDetailActivity.REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                val result = data?.getStringExtra(TopStoryDetailActivity.RESULT_DATA_KEY)
                result?.let { title ->
                    tvFavouriteStoryTitle.setVisible()
                    tvFavouriteStoryTitle.text = title
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[TopStoriesListViewModel::class.java]
    }
}
