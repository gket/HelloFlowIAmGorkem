package com.gk.helloflowiamgorkem.base

import android.os.Bundle

interface LifecycleState {

    fun onCreate(savedInstanceState: Bundle?)

    fun onViewCreated(savedInstanceState: Bundle?)

    fun onDestroyView()

    fun onDestroy()

}