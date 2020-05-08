package com.anggit97.hackernews.ui.topstorieslist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anggit97.hackernews.R
import com.anggit97.hackernews.data.TopStoryDetail
import com.anggit97.hackernews.ui.topstorydetail.TopStoryDetailActivity
import kotlinx.android.synthetic.main.row_item_top_stories.view.*

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
class TopStoriesListAdapter: RecyclerView.Adapter<TopStoriesListAdapter.ViewHolder>() {

    private var topStoriesList = mutableListOf<TopStoryDetail>()

    fun addItem(item: TopStoryDetail){
        this.topStoriesList.add(item)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindView(topStoryDetail: TopStoryDetail) {
            with(itemView){
                tvTitleStory.text = topStoryDetail.title
                tvCommentCount.text = (topStoryDetail.kids?.size ?: 0).toString()
                tvScore.text = (topStoryDetail.score ?: 0).toString()
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, TopStoryDetailActivity::class.java)
                intent.putExtra(TopStoryDetailActivity.STORY_ITEM_KEY, topStoryDetail)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_top_stories, parent, false))
    }

    override fun getItemCount(): Int = topStoriesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(topStoriesList[position])
    }
}