package com.example.movieguideapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieguideapp.data.vo.MainVo

class MainViewModel: ViewModel() {
    //创建livedata 监听数据变化
    val content= MutableLiveData<MainVo>()
    init {
        content.postValue(MainVo("hello"))
    }
}