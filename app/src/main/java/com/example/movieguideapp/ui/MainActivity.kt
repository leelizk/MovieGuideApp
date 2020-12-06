package com.example.movieguideapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.movieguideapp.ui.viewmodel.AlbumListViewModel

class MainActivity : AppCompatActivity() {

    // 通过 DependencyProvider 初始化
     val mViewModel: AlbumListViewModel by viewModel()
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        //创建一个viewModel
        // 这种方式 只能初始化 ViewModel()
       // mViewModel  = ViewModelProviders.of(this).get(AlbumListViewModel::class.java)

        // fetch data
        mViewModel.loadData()

        //print data
        print(mViewModel.getAlbumListData())

    }

}