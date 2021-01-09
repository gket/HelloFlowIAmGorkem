package com.gk.helloflowiamgorkem.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gk.helloflowiamgorkem.base.BaseViewModelFragment
import com.gk.helloflowiamgorkem.databinding.FragmentSearchBinding

class SearchFragment : BaseViewModelFragment<FragmentSearchBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, attachToParent)
    }

    override fun onInitView() {

    }

    override fun onInitListener() {

    }
}