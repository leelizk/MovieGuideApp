package com.example.movieguideapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.movieguideapp.base.common.ConnectionLiveData

class BaseActivityViewModel(application: Application) : AndroidViewModel(application) {
	// 基类增加网络连接状态监听
	val connectionLiveData by lazy { ConnectionLiveData(application) }
}