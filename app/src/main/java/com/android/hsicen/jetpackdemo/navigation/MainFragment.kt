package com.android.hsicen.jetpackdemo.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.hsicen.jetpackdemo.R
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_to_lifecycle.setOnClickListener {
            mViewModel.changeStr("click to lifecycle")

            Navigation.findNavController(it)
                .navigate(R.id.action_mainFragment_to_lifecycleFragment)
        }

        btn_to_navigation.setOnClickListener {
            mViewModel.changeStr("click to navigation")

            Navigation.findNavController(it)
                .navigate(R.id.action_mainFragment_to_navigationFragment)
        }

        btn_to_viewmodel.setOnClickListener {
            mViewModel.changeStr("click to viewmodel")

            Navigation.findNavController(it)
                .navigate(R.id.action_mainFragment_to_viewModelFragment)
        }

        btn_to_livedata.setOnClickListener {
            mViewModel.changeStr("click to livedata")

            Navigation.findNavController(it)
                .navigate(R.id.action_mainFragment_to_livedataFragment)
        }

        mViewModel.mShareStr.observe(viewLifecycleOwner, Observer {
            Log.d("hsc", "MainFragment: $it")
        })
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}