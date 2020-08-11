package com.android.hsicen.jetpackdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * 作者：hsicen  2020/8/11 8:38
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：JetpackDemo
 */
class TimerViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>()
    private val mTimer by lazy {
        Timer()
    }
    private val mTimerTask by lazy {
        object : TimerTask() {
            override fun run() {
                _count.value = _count.value?.plus(1)
            }
        }
    }

    val count: LiveData<Int>
        get() = _count


    fun startTiming() {
        _count.value = 0
        mTimer.schedule(mTimerTask, 1000, 1000)
    }
}