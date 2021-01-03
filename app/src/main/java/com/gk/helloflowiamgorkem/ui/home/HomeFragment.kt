 package com.gk.helloflowiamgorkem.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.gk.helloflowiamgorkem.R
import com.gk.helloflowiamgorkem.adapter.PhotoCardAdapter
import com.gk.helloflowiamgorkem.base.BaseViewModelFragment
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
        binding.imageViewShuffle.setOnClickListener {
            viewModel.shuffle()
        }
    }

    override fun onObserveData() {
        super.onObserveData()
        viewModel.viewState.listen { state ->
            when (state) {
                is HomeViewState.Loading
                -> {
                    showLoading()
                    Log.d("ViewState", "Loading")
                }
                is HomeViewState.UnSplashPhotos -> {
                    hideLoading()
                    setImageBlur(state.list.first().url.thumb) // For first item
                    adapterPhoto?.items = state.list
                    Log.d("ViewState", "UnSplashPhotos")
                }
                is HomeViewState.Error -> {
                    hideLoading()
                    longToast(message = state.error.toString())
                    Log.d("ViewState", "Error")
                }
                is HomeViewState.ShuffleState -> {
                    hideLoading()
                    viewModel.isPending = !state.isEnable
                    if (state.isEnable) {
                        binding.imageViewShuffle.setColorFilter(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.green_enable
                            ), android.graphics.PorterDuff.Mode.SRC_IN
                        )
                    } else {
                        binding.imageViewShuffle.setColorFilter(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.gray_disable
                            ), android.graphics.PorterDuff.Mode.SRC_IN
                        )

                    }
                }
                is HomeViewState.ShowToast -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun setImageBlur(thumb: String) {
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

    override fun clickHandling() {
        binding.imageViewShuffle.setOnClickListener {
            viewModel.shuffle()
        }
    }


}