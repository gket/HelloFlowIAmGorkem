package com.gk.helloflowiamgorkem.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding

abstract class BaseViewModelActivity<VB : ViewBinding, VM : BaseViewModel> : BaseActivity<VB>() {

    abstract val viewModel: VM

    override fun onPreInit(savedInstanceState: Bundle?) {
        super.onPreInit(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}