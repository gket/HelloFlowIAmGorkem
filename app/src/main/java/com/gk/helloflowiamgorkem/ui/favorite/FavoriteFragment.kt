package com.gk.helloflowiamgorkem.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gk.helloflowiamgorkem.adapter.FavoritePhotoAdapter
import com.gk.helloflowiamgorkem.base.BaseViewModelFragment
import com.gk.helloflowiamgorkem.data.Favorite
import com.gk.helloflowiamgorkem.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FavoriteFragment : BaseViewModelFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    private var adapterPhoto = FavoritePhotoAdapter(ArrayList())

    override val viewModel: FavoriteViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, container, attachToParent)
    }

    override fun onInitView() {
        binding.recyclerViewPhotos.adapter = adapterPhoto
    }

    override fun onInitListener() {
        adapterPhoto.onPhotoClicked = {
            val action = FavoriteFragmentDirections.actionFavoritesToPhotoDetailFragment(
                it.url,
                it.user,
                it.id
            )
            findNavController().navigate(action)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.viewState.collect {
                when (it) {
                    is FavoriteViewState.Error -> {
                        // TODO Empty list ui
                    }
                    is FavoriteViewState.FavoritePhotos -> {
                        adapterPhoto.refresh(it.list as ArrayList<Favorite>)
                    }
                }
            }
        }
    }
}
