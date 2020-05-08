package com.anggit97.hackernews.data
import com.google.gson.annotations.SerializedName


/**
 * Created by Anggit Prayogo on 5/8/20.
 */
data class TopStoryDetail(
    @SerializedName("by")
    val `by`: String?,
    @SerializedName("descendants")
    val descendants: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("kids")
    val kids: List<Int>?,
    @SerializedName("score")
    val score: Int?,
    @SerializedName("time")
    val time: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)