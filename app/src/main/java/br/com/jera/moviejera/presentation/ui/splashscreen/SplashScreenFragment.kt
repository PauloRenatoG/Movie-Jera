package br.com.jera.moviejera.presentation.ui.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import br.com.jera.moviejera.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    private val controller by lazy { findNavController() }
    lateinit var direction: NavDirections
    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)

        direction = SplashScreenFragmentDirections.splashScreenFragmentToSearchMovieFragment()
        controller.navigate(direction)

        return binding.root
    }
}