package com.gk.helloflowiamgorkem.ui.photo_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.gk.helloflowiamgorkem.R
import com.gk.helloflowiamgorkem.base.BaseViewModelFragment
import com.gk.helloflowiamgorkem.data.Favorite
import com.gk.helloflowiamgorkem.databinding.FragmentPhotoDetailBinding
import com.gk.helloflowiamgorkem.di.GlideApp
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PhotoDetailFragment :
    BaseViewModelFragment<FragmentPhotoDetailBinding, PhotoDetailViewModel>() {
    override val viewModel: PhotoDetailViewModel by viewModels()
    private val args by navArgs<PhotoDetailFragmentArgs>()
    private var userName: String? = null
    private var url: String? = null
    lateinit var id: String
    private var showMessageEnabled = false

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
        id = args.id
        setUi()
    }

    override fun onInitListener() {
        viewModel.checkIsFavorited(id)

        binding.imageViewFav.setOnClickListener {
            showMessageEnabled = true
            viewModel.addFavorite(Favorite(id, url, userName))
        }
        lifecycleScope.launchWhenCreated {
            viewModel.viewState.collect {
                when (it) {
                    is PhotoDetailViewState.Error -> ""
                    is PhotoDetailViewState.IsFavorited -> {
                        if (it.isFavorited) {
                            showMessage("Item added to favorite list")
                            binding.imageViewFav.setImageDrawable(
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.ic_favorite
                                )
                            )
                        } else {
                            showMessage("Item removed from favorite list")
                            binding.imageViewFav.setImageDrawable(
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.ic_favorite_border
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setUi() {
        binding.textViewUserName.text = userName
        GlideApp.with(requireContext()).load(url).into(binding.imageViewPhoto)
    }

    private fun showMessage(message: String) {
        if (showMessageEnabled)
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}