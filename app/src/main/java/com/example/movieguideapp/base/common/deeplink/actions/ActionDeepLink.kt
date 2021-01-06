package com.example.movieguideapp.base.common.deeplink.actions

import com.example.movieguideapp.base.common.deeplink.DeepLink
import io.reactivex.Single

abstract class ActionDeepLink<T> : DeepLink<Single<T>> {

	data class Result(
		val message: String? = null,
		val actionUri: String? = null
	)
}
