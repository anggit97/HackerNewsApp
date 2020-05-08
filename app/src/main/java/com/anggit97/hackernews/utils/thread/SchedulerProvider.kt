package com.anggit97.hackernews.utils.thread

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
interface SchedulerProvider {
    fun ui(): CoroutineDispatcher
}