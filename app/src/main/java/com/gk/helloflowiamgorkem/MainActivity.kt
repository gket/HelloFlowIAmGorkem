package com.gk.helloflowiamgorkem

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gk.helloflowiamgorkem.base.BaseActivity
import com.gk.helloflowiamgorkem.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var navHostFragment: NavHostFragment

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onInitView() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        navController.let { binding.bottomNavigationView.setupWithNavController(it) }
    }

    override fun onSupportNavigateUp() = findNavController(navHostFragment).navigateUp()

    override fun onInitListener() {

    }


}