package com.gk.helloflowiamgorkem.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gk.helloflowiamgorkem.base.toBinding
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.databinding.ItemPhotoLibraryBinding
import com.gk.helloflowiamgorkem.di.GlideApp

class PhotoLibraryPagingAdapter :
    PagingDataAdapter<UnsplashPhoto, PhotoLibraryPagingAdapter.ViewHolder>(PhotoComparator) {

    lateinit var onPhotoClicked: ((UnsplashPhoto) -> Unit)

    companion object PhotoComparator : DiffUtil.ItemCallback<UnsplashPhoto>() {
        override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.toBinding())
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unSplashPhotoByPositioned = getItem(position)
        unSplashPhotoByPositioned?.let { holder.bind(it) }
    }


    inner class ViewHolder(private val binding: ItemPhotoLibraryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UnsplashPhoto) {
            GlideApp.with(binding.root.context).load(item.urls.regular)
                .into(binding.imageViewLibraryPhoto)

            binding.cardView.setOnClickListener {
                onPhotoClicked.invoke(item)
            }
        }

    }
}

