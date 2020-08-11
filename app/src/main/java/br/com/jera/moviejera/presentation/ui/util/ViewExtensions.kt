package br.com.jera.moviejera.presentation.ui.util

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

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

const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"