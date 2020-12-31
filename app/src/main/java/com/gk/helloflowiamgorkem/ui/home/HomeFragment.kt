package com.gk.helloflowiamgorkem.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.gk.helloflowiamgorkem.databinding.FragmentHomeBinding
import com.gk.helloflowiamgorkem.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getRandomPhoto(0)
        lifecycleScope.launchWhenCreated {
            homeViewModel.uiState.collect {
                when (it) {
                    is Resource.Success -> {
                        updateImage(it.data?.url?.full)
                        updateDescription(it.data?.description)
                        homeViewModel.getRandomPhoto(10000)
                    }
                    is Resource.Error -> {
                        Log.d("ResourceState:", "Error:" + it.message.toString())
                    }
                    is Resource.Loading -> {
                        Log.d("ResourceState:", "Loading:")
                    }
                    else -> Log.d("ResourceState:", "Unexpected")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateImage(imageUrl: String?) {
        Glide.with(this).load(imageUrl).into(binding.imageViewRandomPhoto)
    }

    fun updateDescription(description: String?) {
        binding.textViewDescription.text = description
    }
}