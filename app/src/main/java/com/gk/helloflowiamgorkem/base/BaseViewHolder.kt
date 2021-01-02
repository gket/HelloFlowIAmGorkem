package com.gk.helloflowiamgorkem.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<MyViewHolderBinding> =
    parent.viewBinding(MyViewHolderBinding::inflate)*/

open class BindingViewHolder<T : ViewBinding> private constructor(
    val binding: T
) : RecyclerView.ViewHolder(binding.root) {
    constructor(
        parent: ViewGroup,
        creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T
    ) : this(creator(
        LayoutInflater.from(parent.context),
        parent,
        false
    ))
}

fun <T : ViewBinding> ViewGroup.viewBinding(
    creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T
): BindingViewHolder<T> = BindingViewHolder(this, creator)

inline fun <reified V : ViewBinding> ViewGroup.toBinding(): V {
    return V::class.java.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    ).invoke(null, LayoutInflater.from(context), this, false) as V
}
