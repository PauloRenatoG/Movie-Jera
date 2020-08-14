package br.com.jera.moviejera.presentation.ui.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.jera.moviejera.R
import br.com.jera.moviejera.databinding.ActivitySplashScreenBinding
import br.com.jera.moviejera.presentation.ui.MainActivity
import br.com.jera.moviejera.presentation.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        getCurrentUser()
    }

    private fun getCurrentUser() {
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            openMainActivity()
        } else {
            openLogin()
        }
    }

    private fun openLogin() {
        val intent = LoginActivity.openLogin(this)
        startActivity(intent)
    }

    private fun openMainActivity() {
        val intent = MainActivity.openMainActivity(this)
        startActivity(intent)
    }
}