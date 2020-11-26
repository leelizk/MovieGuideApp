package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import androidx.annotation.CallSuper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieguideapp.base.App
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

	val disposables = CompositeDisposable()

	//这是搞什么飞机
	protected val _errorLiveData = MutableLiveData<Throwable>()
	//记录错误信息
	val errorLiveData: LiveData<Throwable> = _errorLiveData

	//暂不需要监听网络
	//val connectionLiveData by lazy { ConnectionLiveData(application) }

	//val albumListViewModel by lazy{ AlbumListViewModel(application) }


	val app by lazy { getApplication<App>() }

	@CallSuper
	override fun onCleared() {
		super.onCleared()
		disposables.clear()
	}

	/**
	 * Clear error in live data otherwise not cleared value will be posted on subscribe
	 */
	fun clearError() {
		_errorLiveData.value = null
	}

	//常量
	companion object {
		const val ERROR_MESSAGE = "Sorry, something went wrong. Please try again later."
	}



	// rxjava 封装请求...?
	fun rxLaunch(job: () -> Disposable): Disposable {
		return job().apply {
			disposables.add(this)
		}
	}
}