package com.gk.helloflowiamgorkem.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.gk.helloflowiamgorkem.adapter.PhotoCardAdapter
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.databinding.FragmentHomeBinding
import com.gk.helloflowiamgorkem.di.GlideApp
import com.gk.helloflowiamgorkem.utils.Resource
import com.gk.helloflowiamgorkem.utils.WiwwCompositePageTransformer
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.blurry.Blurry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class HomeFragment : Fragment() {

    //TARGET : REDUCE THIS FRAGMENT
    //TODO : 3 - CLEAN
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()
    private var firstPosition = 0
    private var controlPosition = 0

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
        homeViewModel.getRandomPhoto()
        lifecycleScope.launchWhenCreated {
            homeViewModel.uiState.collect {
                when (it) {
                    is Resource.Success -> {
                        it.data?.let { photos -> updateImages(photos) }
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

    fun updateImages(photos: List<UnsplashPhoto>) {
        //TODO : 1 - Will change this part
        val viewPager = binding.viewPager
        viewPager.adapter = context?.let { PhotoCardAdapter(photos, it) }
        viewPager.offscreenPageLimit = 5
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        viewPager.setPageTransformer(WiwwCompositePageTransformer.getCompositePageTransformer())
        controlPosition = firstPosition
        setImageBlur(photos[0].url.thumb) // For first item
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position != controlPosition) {
                    controlPosition = position
                    setImageBlur(photos[controlPosition].url.thumb)
                }
            }
        })
    }

    fun setImageBlur(thumb: String) {
        // TODO 2 - Will change this part
        lifecycleScope.launch(Dispatchers.IO) {
            context?.let {
                val bitmap = GlideApp.with(it)
                    .asBitmap()
                    .load(thumb)
                    .submit()
                    .get()
                withContext(Dispatchers.Main) {
                    Blurry.with(context)
                        .from(bitmap)
                        .into(binding.imageViewBackground)
                }
            }
        }
    }


}