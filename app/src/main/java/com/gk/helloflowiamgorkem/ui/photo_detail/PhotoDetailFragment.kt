package com.gk.helloflowiamgorkem.ui.photo_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gk.helloflowiamgorkem.base.BaseViewModelFragment
import com.gk.helloflowiamgorkem.databinding.FragmentPhotoDetailBinding
import com.gk.helloflowiamgorkem.di.GlideApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailFragment :
    BaseViewModelFragment<FragmentPhotoDetailBinding, PhotoDetailViewModel>() {
    override val viewModel: PhotoDetailViewModel by viewModels()
    private val args by navArgs<PhotoDetailFragmentArgs>()
    private var userName: String? = null
    private var url: String? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentPhotoDetailBinding {
        return FragmentPhotoDetailBinding.inflate(inflater, container, attachToParent)
    }

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
            //TODO viewModel.addFavorite()
        }
    }

    private fun setUi() {
        binding.textViewUserName.text = userName
        GlideApp.with(requireContext()).load(url).into(binding.imageViewPhoto)
    }
}