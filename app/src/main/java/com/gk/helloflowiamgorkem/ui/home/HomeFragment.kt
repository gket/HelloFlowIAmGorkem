package com.gk.helloflowiamgorkem.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.gk.helloflowiamgorkem.adapter.PhotoCardAdapter
import com.gk.helloflowiamgorkem.base.BaseViewModelFragment
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.databinding.FragmentHomeBinding
import com.gk.helloflowiamgorkem.di.GlideApp
import com.gk.helloflowiamgorkem.utils.WiwwCompositePageTransformer
import com.gk.helloflowiamgorkem.utils.listen
import com.gk.helloflowiamgorkem.utils.longToast
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.blurry.Blurry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class HomeFragment : BaseViewModelFragment<FragmentHomeBinding, HomeViewModel>() {

    //TARGET : REDUCE THIS FRAGMENT
    private var firstPosition = 0
    private var controlPosition = 0
    private var adapterPhoto: PhotoCardAdapter? = null

    override val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, attachToParent)
    }

    override fun onInitView() {
        viewModel.getRandomPhoto()
        adapterPhoto = PhotoCardAdapter()
        setViewPager()
    }

    private fun setViewPager() = with(binding.viewPager) {
        offscreenPageLimit = 5
        getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        setPageTransformer(WiwwCompositePageTransformer.getCompositePageTransformer())
        adapter = adapterPhoto
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position != controlPosition) {
                    controlPosition = position
                    setImageBlur(adapterPhoto?.items.orEmpty()[controlPosition].url.thumb)
                }
            }
        })
    }

    override fun onInitListener() {

    }

    override fun onObserveData() {
        super.onObserveData()
        // todo: collect ile uğraşmaman için extension method yazdım
        //  hade gene iyisin :)
        viewModel.viewState.listen { state ->
            when (state) {
                is HomeViewModel.ViewState.Loading -> {
                    showLoading()
                    Log.d("ViewState", "Loading")
                }
                is HomeViewModel.ViewState.UnSplashPhotos -> {
                    hideLoading()
                    adapterPhoto?.items = state.list
                    setImageBlur(adapterPhoto?.items.orEmpty().first().url.thumb) // For first item
                    Log.d("ViewState", "UnSplashPhotos")
                }
                is HomeViewModel.ViewState.Error -> {
                    hideLoading()
                    longToast(message = state.error.toString())
                    Log.d("ViewState", "Error")
                }
            }
        }
    }


    fun updateImages(photos: List<UnsplashPhoto>) {
        //TODO : 1 - Will change this part
        val viewPager = binding.viewPager
        viewPager.adapter = adapterPhoto
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

    override fun onDestroyView() {
        adapterPhoto = null
        super.onDestroyView()
    }


}