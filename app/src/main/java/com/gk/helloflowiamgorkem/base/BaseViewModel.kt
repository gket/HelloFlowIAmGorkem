package com.gk.helloflowiamgorkem.base

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel : ViewModel(), InitState, LifecycleState {

    override var arguments: Bundle? = null
    override var fragmentManager: FragmentManager? = null
    override var activity: WeakReference<FragmentActivity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

    }

    override fun onViewCreated(savedInstanceState: Bundle?) {

    }

    override fun onDestroyView() {

    }

    override fun onDestroy() {

    }


}