package com.gk.helloflowiamgorkem.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB : ViewDataBinding,VM:BaseViewModel>(@LayoutRes val layout:Int) : Fragment() {

    val binding:DB by lazy {
        DataBindingUtil.inflate(layoutInflater,layout,null,false)
    }

    abstract val viewModel: VM

    open fun onObserveData() {}

    abstract fun onInitView()

    abstract fun onInitListener()

    open fun clickHandling() {}

    open fun onPreInit(savedInstanceState: Bundle?) {}

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.lifecycleOwner=viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onPreInit(savedInstanceState)
        onObserveData()
        onInitView()
        onInitListener()
        clickHandling()
    }

    fun hideLoading() {
        (activity as BaseActivity<*>).hideLoading()
    }

    fun showLoading() {
        (activity as BaseActivity<*>).showLoading()
    }
}