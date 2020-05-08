package com.anggit97.hackernews.ui.topstorydetail

import android.app.Activity
import android.content.Intent
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
import com.anggit97.hackernews.utils.ext.showImageDrawable
import com.anggit97.hackernews.utils.state.LoaderState
import kotlinx.android.synthetic.main.activity_top_story_detail.*
import javax.inject.Inject


class TopStoryDetailActivity : BaseActivity() {

    companion object{
        const val STORY_ITEM_KEY = "story_item_key"
        const val REQUEST_CODE = 101
        const val RESULT_DATA_KEY = "result_data_key"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: TopStoryDetailViewModel

    private lateinit var commentAdapter: CommentAdapter
    private var storyItems : TopStoryDetail? = null
    private var favouriteClick = false
    private var favouriteTitle = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_story_detail)
        handleIntent()
        initViewModel()
        observeViewModel()
        initRecyclerViewComment()
        bindStoryDetailToView()
        fetchData()
        onClickListener()
    }

    private fun onClickListener() {
        ivBack.setOnClickListener {
            finishAndSendBackResult()
        }

        ivStar.setOnClickListener {
            handleFavouriteState()
        }
    }

    override fun onBackPressed() {
        finishAndSendBackResult()
    }

    private fun handleFavouriteState() {
        if (!favouriteClick){
            ivStar.showImageDrawable(R.drawable.ic_star_gold_24dp)
        }else{
            ivStar.showImageDrawable(R.drawable.ic_star_border_gold_24dp)
        }
        favouriteTitle = storyItems?.title ?: ""
        favouriteClick = !favouriteClick
    }

    private fun finishAndSendBackResult(){
        val returnIntent = Intent()
        if (favouriteClick) returnIntent.putExtra(RESULT_DATA_KEY, favouriteTitle)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    private fun bindStoryDetailToView() {
        storyItems?.let { data ->
            with(data){
                tvTitleStory.text = title
                tvByStory.text = getString(R.string.create_by_template, by)
                tvDateStory.text = time?.toLong()?.let { DateUtils.parseUnixTimeToFriendlyDate(it) }
                tvDescStoryBody.text = title
                tvScoreStory.text = getString(R.string.score_template, score.toString())
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
