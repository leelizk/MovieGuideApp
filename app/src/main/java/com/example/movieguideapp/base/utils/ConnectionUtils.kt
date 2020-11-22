package com.example.movieguideapp.base.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object ConnectionUtils {

    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager?.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}