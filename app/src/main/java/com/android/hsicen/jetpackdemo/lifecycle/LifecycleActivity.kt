package com.android.hsicen.jetpackdemo.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.hsicen.jetpackdemo.R
import com.hsicen.dnfmobile.test.MyLocator

/**
 * 作者：hsicen  2020/8/11 10:23
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：lifecycle
 */
class LifecycleActivity : AppCompatActivity() {

    private val myLocator by lazy {
        MyLocator { lat, lng ->
            println("当前位置: $lat , $lng")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        //注册监听
        lifecycle.addObserver(myLocator)
    }
}