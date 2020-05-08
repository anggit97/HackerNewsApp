package com.anggit97.hackernews.ui.topstorydetail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anggit97.hackernews.R
import com.anggit97.hackernews.base.BaseActivity
import com.anggit97.hackernews.data.Comment
import com.anggit97.hackernews.data.TopStoryDetail
import com.anggit97.hackernews.utils.date.DateUtils
import com.anggit97.hackernews.utils.ext.setGone
import com.anggit97.hackernews.utils.ext.setVisible
import com.anggit97.hackernews.utils.state.LoaderState
import com.anggit97.hackernews.utils.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_top_story_detail.*
import javax.inject.Inject

class TopStoryDetailActivity : BaseActivity() {

    companion object{
        const val STORY_ITEM_KEY = "story_item_key"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: TopStoryDetailViewModel

    private lateinit var commentAdapter: CommentAdapter
    private var storyItems : TopStoryDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_story_detail)
        handleIntent()
        initViewModel()
        observeViewModel()
        initRecyclerViewComment()
        bindStoryDetailToView()
        fetchData()
    }

    private fun bindStoryDetailToView() {
        storyItems?.let { data ->
            with(data){
                tvTitleStory.text = title
                tvByStory.text = getString(R.string.create_by_template, by)
                tvDateStory.text = time?.toLong()?.let { DateUtils.parseUnixTimeToFriendlyDate(it) }
                tvDescStoryBody.text = title
            }
        }
    }

    private fun fetchData() {
        storyItems?.let { data ->
            data.kids?.forEach {
                viewModel.getComment(it.toString())
            }
        }
    }

    private fun handleIntent() {
        storyItems = intent.getParcelableExtra(STORY_ITEM_KEY)
    }

    private fun initRecyclerViewComment() {
        commentAdapter = CommentAdapter()
        rvComment.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvComment.adapter = commentAdapter
    }

    private fun observeViewModel() {
        viewModel.items.observe(this, Observer {
            it?.let {
                handleCommentViewModel(it)
            }
        })

        viewModel.state.observe(this, Observer {
            it?.let {
                handleLoaderState(it)
            }
        })
    }

    private fun handleLoaderState(loading: LoaderState) {
        if (loading is LoaderState.ShowLoading){
            progressBarComment.setVisible()
        }else{
            progressBarComment.setGone()
        }
    }

    private fun handleCommentViewModel(data: Comment) {
        commentAdapter.addItem(data)
    }

    @Suppress("DEPRECATION")
    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[TopStoryDetailViewModel::class.java]
    }
}
