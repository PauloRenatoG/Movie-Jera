package br.com.jera.moviejera.presentation.ui.util

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

fun Fragment.setupToolbar(toolbar: Toolbar?) {
    (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
    (activity as? AppCompatActivity)?.title = null
}