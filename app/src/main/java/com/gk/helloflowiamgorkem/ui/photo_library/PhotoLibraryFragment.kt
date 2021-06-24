package com.gk.helloflowiamgorkem.ui.photo_library

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gk.helloflowiamgorkem.R
import com.gk.helloflowiamgorkem.adapter.PhotoLibraryPagingAdapter
import com.gk.helloflowiamgorkem.base.BaseFragment
import com.gk.helloflowiamgorkem.databinding.FragmentPhotosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoLibraryFragment : BaseFragment<FragmentPhotosBinding, PhotoLibraryViewModel>(R.layout.fragment_photos) {

    private var adapterPhoto: PhotoLibraryPagingAdapter? = null

    override val viewModel: PhotoLibraryViewModel by viewModels()

    override fun onInitView() {
        adapterPhoto = PhotoLibraryPagingAdapter()
        binding.recyclerViewPhotos.adapter = adapterPhoto

        adapterPhoto?.onPhotoClicked = {
            val action = PhotoLibraryFragmentDirections.actionPhotosToPhotoDetailFragment(
                it.urls.full,
                it.user.username
            )

            findNavController().navigate(action)
        }

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