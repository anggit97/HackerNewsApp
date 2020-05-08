package com.anggit97.hackernews.utils.state

/**
 * Created by Anggit Prayogo on 5/8/20.
 */
sealed class LoaderState {
    object ShowLoading: LoaderState()
    object HideLoading: LoaderState()
}