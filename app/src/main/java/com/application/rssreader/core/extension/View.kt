package com.application.rssreader.core.extension

import android.view.View
import android.widget.ImageView
import com.application.rssreader.R
import com.bumptech.glide.Glide


fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun ImageView.loadFromUrl(url: String?) =
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.progress_anim)
        .error(R.drawable.progress_anim)
        .skipMemoryCache(false)
        .into(this)

