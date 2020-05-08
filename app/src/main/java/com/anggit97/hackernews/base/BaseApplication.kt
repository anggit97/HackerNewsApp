package com.anggit97.hackernews.base

import android.R
import com.anggit97.hackernews.config.FontConfig
import com.anggit97.hackernews.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump


/**
 * Created by Anggit Prayogo on 5/8/20.
 */
class BaseApplication: DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        initFont()
    }

    private fun initFont() {
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath(FontConfig.FONT_NORMAL)
                            .setFontAttrId(R.attr.fontFamily)
                            .build()
                    )
                )
                .build()
        )
    }
}