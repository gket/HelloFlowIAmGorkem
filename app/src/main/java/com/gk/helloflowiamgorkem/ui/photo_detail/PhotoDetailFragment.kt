package com.gk.helloflowiamgorkem.ui.photo_detail

import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gk.helloflowiamgorkem.R
import com.gk.helloflowiamgorkem.base.BaseFragment
import com.gk.helloflowiamgorkem.databinding.FragmentPhotoDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailFragment :
    BaseFragment<FragmentPhotoDetailBinding, PhotoDetailViewModel>(R.layout.fragment_photo_detail) {
    override val viewModel: PhotoDetailViewModel by viewModels()
    private val args by navArgs<PhotoDetailFragmentArgs>()
    private var userName: String? = null
    private var url: String? = null

    override fun onInitView() {
        userName = args.userName
        url = args.photoUrl
        setUi()
    }

    override fun onInitListener() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            val action = PhotoDetailFragmentDirections.actionPhotoDetailFragmentToPhotos()
            findNavController().navigate(action)
        }
        binding.imageViewFav.setOnClickListener {
            viewModel.manageFavorite(url.toString())
        }
    }

    private fun setUi() {
        binding.apply {
            this.userName = this@PhotoDetailFragment.userName
            this.url = this@PhotoDetailFragment.url
        }
    }
}