package com.gk.helloflowiamgorkem.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import java.lang.ref.WeakReference

abstract class BaseViewModelFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFragment<VB>() {

    abstract val viewModel: VM

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.arguments = arguments
        viewModel.fragmentManager = childFragmentManager
        viewModel.activity = WeakReference(activity)
        viewModel.onCreate(savedInstanceState)
    }

    override fun onPreInit(savedInstanceState: Bundle?) {
        super.onPreInit(savedInstanceState)
        viewModel.onViewCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

}