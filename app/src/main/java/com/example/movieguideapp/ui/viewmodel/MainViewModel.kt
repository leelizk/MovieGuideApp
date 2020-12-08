package com.example.movieguideapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    //创建livedata 监听数据变化
    val content= MutableLiveData<String>()
    init {
        content.postValue("hello")
    }
}