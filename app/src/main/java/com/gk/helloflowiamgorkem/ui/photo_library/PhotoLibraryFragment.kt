package com.gk.helloflowiamgorkem.ui.photo_library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gk.helloflowiamgorkem.adapter.PhotoLibraryPagingAdapter
import com.gk.helloflowiamgorkem.base.BaseViewModelFragment
import com.gk.helloflowiamgorkem.databinding.FragmentPhotosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoLibraryFragment : BaseViewModelFragment<FragmentPhotosBinding, PhotoLibraryViewModel>() {

    private var adapterPhoto: PhotoLibraryPagingAdapter? = null

    override val viewModel: PhotoLibraryViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentPhotosBinding {
        return FragmentPhotosBinding.inflate(inflater, container, attachToParent)
    }

    override fun onInitView() {
        adapterPhoto = PhotoLibraryPagingAdapter()
        binding.recyclerViewPhotos.adapter = adapterPhoto
        viewModel.getPhotos()
        lifecycleScope.launch {
            viewModel.viewState.collect {
                adapterPhoto?.submitData(lifecycle, it)
            }
        }
    }

    override fun onInitListener() {
        //
    }


}