package com.example.movieguideapp.base.utils

import android.net.Uri

class UriWrapper {

    fun encode(s: String?): String? = Uri.encode(s)

    fun parse(s: String): Uri = Uri.parse(s)
}
