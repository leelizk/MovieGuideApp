package com.example.movieguideapp.base.common.error

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class ErrorHandler(
    private val activity: FragmentActivity,
    lifecycleOwner: LifecycleOwner,
    errorLiveData: LiveData<Throwable>,
    onErrorProcessed: (Throwable) -> Unit
) {

    init {
        errorLiveData.observe(lifecycleOwner, Observer { error ->
            error?.run {
                onErrorProcessed(this)
            }
        })
    }
}