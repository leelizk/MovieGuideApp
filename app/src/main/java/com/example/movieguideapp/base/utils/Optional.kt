package com.example.movieguideapp.base.utils

data class Optional<T>(val value: T?) {

    companion object {
        fun <T> empty(): Optional<T> = Optional(null)
    }
}
