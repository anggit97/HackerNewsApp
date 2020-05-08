package com.anggit97.hackernews.base

import android.content.Context
import dagger.android.support.DaggerAppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper




/**
 * Created by Anggit Prayogo on 5/8/20.
 */
abstract class BaseActivity: DaggerAppCompatActivity(){

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { ViewPumpContextWrapper.wrap(it) })
    }
}