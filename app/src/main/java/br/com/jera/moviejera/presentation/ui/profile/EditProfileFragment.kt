package br.com.jera.moviejera.presentation.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.jera.moviejera.databinding.FragmentEditProfileBinding
import br.com.jera.moviejera.presentation.ui.util.loadImageProfile
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        getFirebaseUser()
        subscribeUi()
        setupBinding()
        return binding.root
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            viewModel.user.collect {
                with(binding) {
                    editTextName.setText(it.name)
                    editTextEmail.setText(it.email)
                    editTextDate.setText(it.dateOfBirth)
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
        with(binding) {
            editTextDate.setOnClickListener {

            }

            buttonSave.setOnClickListener {
                viewModel.updateUser(
                    editTextName.text.toString(),
                    editTextEmail.text.toString(),
                    editTextDate.text.toString()
                )
            }
        }
    }
}