package com.gk.helloflowiamgorkem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gk.helloflowiamgorkem.data.UnsplashPhoto
import com.gk.helloflowiamgorkem.databinding.ItemPhotoCardBinding
import com.gk.helloflowiamgorkem.di.GlideApp
import kotlinx.android.synthetic.main.item_photo_card.view.*

class PhotoCardAdapter(
    private val unsplashPhotos: List<UnsplashPhoto>,
    private val context: Context
) :
    RecyclerView.Adapter<PhotoCardAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemPhotoCardBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhotoCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = unsplashPhotos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unsplashPhotoByPositioned = unsplashPhotos[position]
        GlideApp.with(context).load(unsplashPhotoByPositioned.url.regular)
            .into(holder.itemView.imageViewPhoto)
    }
}