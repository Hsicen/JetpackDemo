package com.android.hsicen.jetpackdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {

        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    /*** 发送通知*/
    private fun sendNotification() {
        val manager = NotificationManagerCompat.from(this)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel(CHANNEL_ID, "通知", importance)
            notificationChannel.description = "重要通知"
            manager.createNotificationChannel(notificationChannel)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentText("DeepLink Demo")
            .setContentTitle("DeepLink Demo")
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(getPendIntent())
            .setAutoCancel(true)
            .build()


        manager.notify(1, notification)
    }

    private fun getPendIntent(): PendingIntent {
        val bundle = Bundle()
        bundle.putString("params", "Hello hsicen")

        return Navigation.findNavController(this, R.id.btn_to_second)
            .createDeepLink()
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.secondFragment) //nav_graph中的目的fragment
            .setArguments(bundle)
            .createPendingIntent()
    }

    companion object {
        const val CHANNEL_ID = "231"
    }
}