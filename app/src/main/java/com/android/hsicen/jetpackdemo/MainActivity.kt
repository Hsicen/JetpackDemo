package com.android.hsicen.jetpackdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    private val navController by lazy {
        Navigation.findNavController(this, R.id.main_nav_host)
    }

    private val appBarConfiguration by lazy {
        AppBarConfiguration.Builder(navController.graph).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //绑定ActionBar和NavController
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            //页面切换回调监听

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        //支持返回功能
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}