package br.com.jera.moviejera.presentation

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieDBApplication : Application() {

    var currentUser: FirebaseUser? = null

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        currentUser = FirebaseAuth.getInstance().currentUser
    }
}