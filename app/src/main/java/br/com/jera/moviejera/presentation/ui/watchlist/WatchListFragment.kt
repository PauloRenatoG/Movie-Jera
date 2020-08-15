package br.com.jera.moviejera.presentation.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.jera.moviejera.R
import br.com.jera.moviejera.databinding.FragmentWatchListBinding
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.presentation.MovieDBApplication
import br.com.jera.moviejera.presentation.ui.util.setupToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@AndroidEntryPoint
class WatchListFragment : Fragment() {

    private lateinit var binding: FragmentWatchListBinding
    private val viewModel: WatchListViewModel by viewModels()
    private val watchListAdapter: WatchListAdapter = WatchListAdapter { removeFromWatchList(it) }
    private val movieDbApp by lazy { activity?.application as MovieDBApplication }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentWatchListBinding.inflate(inflater, container, false)
        setupToolbar(binding.includedToolbar.toolbar, getString(R.string.watch_list))
        lifecycle.addObserver(viewModel)

        viewModel.getUser(movieDbApp.currentUser?.email)
        subscribeUi()
        setupRecycler()
        return binding.root
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            viewModel.watchListMovie.observe(viewLifecycleOwner, Observer {
                watchListAdapter.submitList(it)
            })
        }
    }

    private fun setupRecycler() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = watchListAdapter
        }
    }

    private fun removeFromWatchList(movie: Movie) {
        viewModel.remove(movie)
    }
}