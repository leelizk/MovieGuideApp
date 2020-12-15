package com.example.movieguideapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movieguideapp.R
import com.example.movieguideapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName;
    }

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        //通过id获取 NavHostFragment
        navController = findNavController(R.id.container)
        Log.i(TAG,"navController :: " + navController)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        Log.i(TAG,"appBarConfiguration :: " + appBarConfiguration)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

}