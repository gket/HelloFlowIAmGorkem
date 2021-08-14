package com.gk.helloflowiamgorkem.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gk.helloflowiamgorkem.base.toBinding
import com.gk.helloflowiamgorkem.data.Favorite
import com.gk.helloflowiamgorkem.databinding.ItemPhotoLibraryBinding
import com.gk.helloflowiamgorkem.di.GlideApp

class FavoritePhotoAdapter(var favorites: ArrayList<Favorite>) :
    RecyclerView.Adapter<FavoritePhotoAdapter.ViewHolder>() {

    lateinit var onPhotoClicked: ((Favorite) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.toBinding())
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(favorites[position]) {
            holder.bind(this)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(newFavoriteList: ArrayList<Favorite>) {
        favorites.clear()
        favorites.addAll(newFavoriteList)
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemPhotoLibraryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Favorite) {
            GlideApp.with(binding.root.context).load(item.url)
                .into(binding.imageViewLibraryPhoto)

            binding.cardView.setOnClickListener {
                onPhotoClicked.invoke(item)
            }
        }

    }

    override fun getItemCount(): Int {
        return favorites.size
    }
}