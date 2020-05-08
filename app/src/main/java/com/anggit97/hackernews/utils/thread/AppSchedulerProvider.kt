package com.anggit97.hackernews.utils.thread

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
class AppSchedulerProvider: SchedulerProvider {
    override fun ui(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}