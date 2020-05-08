package com.anggit97.hackernews.utils.date

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
object DateUtils {

    @SuppressLint("SimpleDateFormat")
    fun parseUnixTimeToFriendlyDate(unixtime: Long, formatDate: String = "dd/mm/yyyy"): String{
        val date = Date(unixtime * 1000L)
        val sdf = SimpleDateFormat(formatDate)
        sdf.timeZone = TimeZone.getTimeZone("GMT-7")
        return sdf.format(date)
    }
}