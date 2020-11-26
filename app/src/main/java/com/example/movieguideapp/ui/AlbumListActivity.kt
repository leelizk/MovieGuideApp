package com.example.movieguideapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movieguideapp.R
import com.example.movieguideapp.databinding.ActivityAlbumlistBinding
import com.example.movieguideapp.databinding.FragmentAlbumListBinding
import com.example.movieguideapp.ui.viewmodel.AlbumListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumListActivity : AppCompatActivity() {

    companion object{
        val TAG:String = AlbumListActivity::class.java.simpleName;
    }
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var binding:ActivityAlbumlistBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_albumlist);
        //使用内部控制打开 fragment ???
        navController = findNavController(R.id.container)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}