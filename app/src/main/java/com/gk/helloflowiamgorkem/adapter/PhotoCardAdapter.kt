package com.gk.helloflowiamgorkem.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gk.helloflowiamgorkem.base.toBinding
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.databinding.ItemPhotoCardBinding
import com.gk.helloflowiamgorkem.di.GlideApp

class PhotoCardAdapter : RecyclerView.Adapter<PhotoCardAdapter.ViewHolder>() {

    var items: List<UnsplashPhoto> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.toBinding())
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unSplashPhotoByPositioned = items[position]
        holder.bind(unSplashPhotoByPositioned)
    }


    inner class ViewHolder(private val binding: ItemPhotoCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UnsplashPhoto) {
            GlideApp.with(binding.root.context).load(item.url.regular)
                .into(binding.imageViewPhoto)
        }

    }
}