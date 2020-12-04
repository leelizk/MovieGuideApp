package com.example.movieguideapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.movieguideapp.R
import com.example.movieguideapp.ui.vo.MainVo
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
            mViewModel.content.postValue(MainVo("hey....you" + tmp))
            tmp ++;
        }


        //打开 相册列表
        showList.setOnClickListener{
            var intent: Intent = Intent();
            intent.setClass(this,AlbumListActivity::class.java)
            startActivity(intent);
            finish()
        }


    }

}