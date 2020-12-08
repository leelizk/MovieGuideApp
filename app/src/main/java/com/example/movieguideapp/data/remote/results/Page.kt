package com.example.movieguideapp.data.remote.results

data class Page<T> (
        private val page:Int?,
        private val results:List<T>?,
        private val total_pages:Int?,
        private val total_results:Int?
)