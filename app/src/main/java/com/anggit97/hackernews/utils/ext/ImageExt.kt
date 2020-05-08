package com.anggit97.hackernews.utils.ext

import android.widget.ImageView
import androidx.core.content.ContextCompat

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
fun ImageView.showImageDrawable(drawable: Int){
    setImageDrawable(ContextCompat.getDrawable(context, drawable))
}