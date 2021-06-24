package com.gk.helloflowiamgorkem.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gk.helloflowiamgorkem.widgets.LoadingDialog

abstract class BaseActivity<DB : ViewDataBinding>(@LayoutRes val layout: Int) :
    AppCompatActivity() {

    private var loadingDialog: LoadingDialog? = null

    lateinit var binding: DB

    open fun onObserveData() {}

    abstract fun onInitView()

    abstract fun onInitListener()

    open fun onPreInit(savedInstanceState: Bundle?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout)
        binding.lifecycleOwner = this
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