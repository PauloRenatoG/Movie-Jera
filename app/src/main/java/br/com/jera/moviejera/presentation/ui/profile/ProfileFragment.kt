package br.com.jera.moviejera.presentation.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.jera.moviejera.databinding.FragmentProfileBinding
import br.com.jera.moviejera.presentation.ui.login.LoginActivity
import br.com.jera.moviejera.presentation.ui.util.loadImageProfile
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        getFirebaseUser()
        subscribeUi()
        setupBinding()
        return binding.root
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            viewModel.user.collect {
                with(binding) {
                    nameProfile.text = it.name
                    emailProfile.text = it.email
                    dateOfBirthProfile.text = it.dateOfBirth
                    imageProfile.loadImageProfile(it.photoUrl)
                }
            }
        }
    }

    private fun getFirebaseUser() {
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            viewModel.getUser(auth.currentUser!!.email)
        }
    }

    private fun setupBinding() {
        binding.buttonLogout.setOnClickListener {
            context?.let { context ->
                AuthUI.getInstance()
                    .signOut(context)
                    .addOnCompleteListener {
                        val intent = LoginActivity.openLogin(context)
                        startActivity(intent)
                    }
            }
        }

        binding.buttonEdit.setOnClickListener {
            val directions = ProfileFragmentDirections
                .profileFragmentToEditProfileFragment()

            controller.navigate(directions)
        }
    }
}