package com.gk.helloflowiamgorkem.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gk.helloflowiamgorkem.adapter.PhotoLibraryPagingAdapter
import com.gk.helloflowiamgorkem.base.BaseViewModelFragment
import com.gk.helloflowiamgorkem.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseViewModelFragment<FragmentSearchBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()
    private lateinit var adapterPhoto: PhotoLibraryPagingAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, attachToParent)
    }

    override fun onInitView() {
        adapterPhoto = PhotoLibraryPagingAdapter()
        binding.recyclerViewPhotos.adapter = adapterPhoto

        adapterPhoto.onPhotoClicked = {
            val action = SearchFragmentDirections.actionSearchToPhotoDetailFragment(
                it.urls.full,
                it.user.username
            )
            findNavController().navigate(action)
        }

        lifecycleScope.launch {
            viewModel.viewState.collect {
                adapterPhoto.submitData(lifecycle, it)
            }
        }
    }

    override fun onInitListener() {
        searchProcess()
    }

    private fun searchProcess() {
        binding.searchView.isFocusable = true;
        binding.searchView.isFocusableInTouchMode = false
        binding.searchView.requestFocusFromTouch();
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getFavoritePhotos(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })
    }


}