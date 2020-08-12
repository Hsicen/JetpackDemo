package com.android.hsicen.jetpackdemo.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 作者：hsicen  2020/8/12 8:49
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：数据共享
 */
class ShareViewModel : ViewModel() {

    private val _mShareStr = MutableLiveData<String>()

    val mShareStr: LiveData<String>
        get() = _mShareStr


    fun changeStr(tmp: String) {
        _mShareStr.value = tmp
    }

}