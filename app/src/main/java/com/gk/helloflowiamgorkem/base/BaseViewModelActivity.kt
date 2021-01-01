package com.gk.helloflowiamgorkem.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import java.lang.ref.WeakReference

abstract class BaseViewModelActivity<VB : ViewBinding, VM : BaseViewModel> : BaseActivity<VB>() {

    abstract val viewModel: VM

    override fun onPreInit(savedInstanceState: Bundle?) {
        super.onPreInit(savedInstanceState)
        viewModel.arguments = intent.extras
        viewModel.fragmentManager = supportFragmentManager
        viewModel.activity = WeakReference(this)
        viewModel.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

}