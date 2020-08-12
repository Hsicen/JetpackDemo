package com.android.hsicen.jetpackdemo.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hsicen.jetpackdemo.R
import com.android.hsicen.jetpackdemo.lifecycle.LifecycleActivity
import kotlinx.android.synthetic.main.fragment_lifecycle.*


/**
 * A simple [Fragment] subclass.
 * Use the [LifecycleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LifecycleFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val mViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ShareViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lifecycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_lifecycle_1.setOnClickListener {
            startActivity(Intent(activity, LifecycleActivity::class.java))
        }

        mViewModel.mShareStr.observe(viewLifecycleOwner, Observer {
            Log.d("hsc", "Lifecycle: $it")
        })
    }

    override fun onDestroy() {
        mViewModel.changeStr("back from Lifecycle")

        super.onDestroy()
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LifecycleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}