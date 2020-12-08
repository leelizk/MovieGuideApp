package com.example.movieguideapp.data.remote.results

data class Page<T> (
         val page:Int?,
         val results:List<T>?,
         val total_pages:Int?,
         val total_results:Int?
)