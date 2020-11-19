package com.example.movieguideapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.movieguideapp.R
import com.example.movieguideapp.data.vo.AlbumItem
import com.example.movieguideapp.data.vo.MainVo
import com.example.movieguideapp.databinding.ActivityMainBinding
import com.example.movieguideapp.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
         var tmp:Int = 1;
    }

    lateinit var mBinding:ActivityMainBinding;
    lateinit var mViewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        //获取绑定对象
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //模拟数据
        var ablumList:MutableList<AlbumItem> = mutableListOf();
        for(i in 1..21){
            var oneItem:AlbumItem = AlbumItem(i,"测试"+i,"");
            ablumList.add(oneItem);
        }
        //创建一个viewModel
        mViewModel  = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //viewModel 监听数据变化
        mViewModel.content?.observe(this, Observer<MainVo> { cur ->
                    text.setText(cur.text)
            })

        //绑定到 layout data
        mBinding.mainViewModel = mViewModel

        //点击事件
        clickMe.setOnClickListener{

            //提交数据变化
            mViewModel.content.postValue(MainVo("fuk....you" + tmp))

            tmp ++;
        }


    }

}