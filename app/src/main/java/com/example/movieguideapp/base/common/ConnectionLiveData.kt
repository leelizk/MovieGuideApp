package com.example.movieguideapp.base.common

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.movieguideapp.base.utils.ConnectionUtils

class ConnectionLiveData(
	private val application: Application,
	private val networkRequest: NetworkRequest = CELLULAR_OR_WIFI
) : LiveData<ConnectionStatus>() {

	@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
	companion object {
		val CELLULAR_OR_WIFI: NetworkRequest by lazy {
			NetworkRequest.Builder()
				.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
				.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
				.build()
		}
	}

	private val connectivityManager: ConnectivityManager by lazy {
		application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	}

	private val connectivityCallback: ConnectivityManager.NetworkCallback by lazy {
		@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
		object : ConnectivityManager.NetworkCallback() {
			override fun onAvailable(network: Network?) {
				postConnectionStatus(true)
			}

			override fun onLost(network: Network?) {
				postConnectionStatus(false)
			}
		}
	}

	@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
	override fun onActive() {
		super.onActive()

		postConnectionStatus(ConnectionUtils.isConnected(application))

		connectivityManager.registerNetworkCallback(networkRequest, connectivityCallback)
	}

	@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
	override fun onInactive() {
		super.onInactive()

		connectivityManager.unregisterNetworkCallback(connectivityCallback)
	}

	private fun postConnectionStatus(isConnected: Boolean) {
		val status = if (isConnected) ConnectionStatus.CONNECTED else ConnectionStatus.DISCONNECTED
		postValue(status)
	}
}