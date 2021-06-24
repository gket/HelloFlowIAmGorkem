package com.gk.helloflowiamgorkem.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gk.helloflowiamgorkem.R

@BindingAdapter("loadImageUrl")
fun ImageView.loadImageUrl(url: String?) {
    Glide.with(this).load(url).placeholder(R.drawable.shape_progressbar_loading).centerCrop()
        .into(this)
}