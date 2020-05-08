package com.anggit97.hackernews.data
import com.google.gson.annotations.SerializedName


/**
 * Created by Anggit Prayogo on 5/8/20.
 */
data class Comment(
    @SerializedName("by")
    val `by`: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("kids")
    val kids: List<Int>?,
    @SerializedName("parent")
    val parent: Int?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("time")
    val time: Int?,
    @SerializedName("type")
    val type: String?
)