package com.gk.helloflowiamgorkem.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.gk.helloflowiamgorkem.widgets.LoadingDialog

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var loadingDialog: LoadingDialog? = null

    lateinit var binding: VB

    abstract fun getViewBinding(): VB

    open fun onObserveData() {}

    abstract fun onInitView()

    abstract fun onInitListener()

    open fun onPreInit(savedInstanceState: Bundle?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        onPreInit(savedInstanceState)
        onObserveData()
        onInitView()
        onInitListener()
    }

    fun hideLoading() {
        loadingDialog?.dismiss()
    }

    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        loadingDialog?.show()
    }


}