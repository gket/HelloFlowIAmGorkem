package com.gk.helloflowiamgorkem.base

import android.os.Bundle

// todo: geriye kalan metotları kullanmak istersen ekleyebilirsin. mesela onResume() :)
interface LifecycleState {

    fun onCreate(savedInstanceState: Bundle?)

    fun onViewCreated(savedInstanceState: Bundle?)

    fun onDestroyView()

    fun onDestroy()

}