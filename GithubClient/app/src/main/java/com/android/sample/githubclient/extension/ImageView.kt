package com.android.sample.githubclient.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageWithGlide(url: String) {
    Glide.with(context).load(url).centerCrop().into(this)
}