package com.gk.helloflowiamgorkem.base

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import java.lang.ref.WeakReference

interface InitState {
    var arguments: Bundle?

    var fragmentManager: FragmentManager?

    var activity: WeakReference<FragmentActivity>?
}