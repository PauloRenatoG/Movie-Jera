package br.com.jera.moviejera.presentation.ui.util

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

fun Fragment.setupToolbar(toolbar: Toolbar?, title: String? = null) {
    (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
    (activity as? AppCompatActivity)?.title = title
}

fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load("$IMAGE_URL$url")
        .centerCrop()
        .into(this)
}

fun ImageView.loadImageProfile(url: String?) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .transform(CircleCrop())
        .into(this)
}

const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"