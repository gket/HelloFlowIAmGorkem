package com.gk.helloflowiamgorkem.ui.home

import android.content.Context
import androidx.core.content.ContextCompat
import com.gk.helloflowiamgorkem.R

object HomeUiState {
    fun getShuffleColor(context: Context, isClicked: Boolean): Int {
        return if (isClicked) {
            ContextCompat.getColor(context, R.color.teal_200)
        } else {
            ContextCompat.getColor(context, R.color.purple_500)
        }
    }
}