package com.android.hsicen.jetpackdemo.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hsicen.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_view_model.*


class ViewModelActivity : AppCompatActivity() {
    private val mViewModel by lazy {
        ViewModelProvider(this)[TimerViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        mViewModel.count.observe(this, Observer {
            tv_count.text = "$it"
        })

        mViewModel.startTiming()
    }
}