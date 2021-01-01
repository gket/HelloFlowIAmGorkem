package com.gk.helloflowiamgorkem.utils

import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import kotlin.math.abs

object WiwwCompositePageTransformer {

    fun getCompositePageTransformer() : CompositePageTransformer{
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(60))
        compositePageTransformer.addTransformer { page, position ->
            page.scaleY = 0.70f + (1 - abs(position)) * 0.10f
        }
        return compositePageTransformer
    }

}