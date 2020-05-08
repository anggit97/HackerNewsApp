package com.anggit97.hackernews.ui.topstorydetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anggit97.hackernews.R
import com.anggit97.hackernews.data.Comment
import kotlinx.android.synthetic.main.row_item_comment.view.*

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
class CommentAdapter: RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private val commentList = mutableListOf<Comment>()

    fun addItem(comment: Comment){
        commentList.add(comment)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindView(comment: Comment) {
            with(itemView){
                tvCommentText.text = comment.text
                tvCommentby.text = comment.by
                tvCommentTime.text = comment.time.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_comment, parent, false))
    }

    override fun getItemCount(): Int = commentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(commentList[position])
    }
}