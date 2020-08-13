package br.com.jera.moviejera.presentation.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.jera.moviejera.R
import br.com.jera.moviejera.databinding.ActivityLoginBinding
import br.com.jera.moviejera.presentation.ui.MainActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var currentUser: FirebaseUser
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        FirebaseApp.initializeApp(this)

        binding.loginEmail.setOnClickListener {
            startActivityLogin()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                currentUser = FirebaseAuth.getInstance().currentUser!!
                viewModel.saveUser(currentUser)
                openMainActivity()
            } else {
                if (response == null) {
                    showToast(getString(R.string.sign_in_cancelled))
                    return
                }

                if (response.error?.errorCode == ErrorCodes.NO_NETWORK) {
                    showToast(getString(R.string.no_network))
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun startActivityLogin() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.movie_db_logo)
                .setTheme(R.style.FirebaseTheme)
                .build(),
            RC_SIGN_IN
        )
    }

    private fun openMainActivity() {
        val intent = MainActivity.openMainActivity(this)
        startActivity(intent)
    }

    companion object {
        private val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
        private const val RC_SIGN_IN = 10

        fun openLogin(context: Context): Intent {
            return Intent(context, LoginActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }
}