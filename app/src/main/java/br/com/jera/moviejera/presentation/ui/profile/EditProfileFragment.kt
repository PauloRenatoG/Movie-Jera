package br.com.jera.moviejera.presentation.ui.profile

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.jera.moviejera.databinding.FragmentEditProfileBinding
import br.com.jera.moviejera.presentation.ui.util.loadImageProfile
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

@InternalCoroutinesApi
@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private var datePickerDialog: DatePickerDialog? = null
    private val calendar = Calendar.getInstance()
    private val controller by lazy { findNavController() }

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
                    editTextDate.text = it.dateOfBirth
                    imageProfile.loadImageProfile(it.photoUrl)
                }
            }
        }
        viewModel.complete.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            onCompleteSave(it)
        })
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
                createDatePicker()
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

    private fun onCompleteSave(complete: Boolean) {
        if (complete) {
            val directions = EditProfileFragmentDirections.editProfileFragmentToProfileFragment()
            controller.navigate(directions)
        }
    }

    private fun createDatePicker() {
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        datePickerDialog = context?.let {
            DatePickerDialog(it, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val date = String.format("%d/%d/%d", dayOfMonth, month.plus(1), year)
                binding.editTextDate.text = date
            }, day, month, year)
        }
        datePickerDialog?.show()
    }
}