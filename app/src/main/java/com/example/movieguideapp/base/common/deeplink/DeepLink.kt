package com.example.movieguideapp.base.common.deeplink

interface DeepLink<out T> {
    fun provideCommand(): T
}
