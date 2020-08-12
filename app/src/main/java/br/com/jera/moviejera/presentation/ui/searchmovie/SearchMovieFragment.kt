package br.com.jera.moviejera.presentation.ui.searchmovie

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.jera.moviejera.R
import br.com.jera.moviejera.databinding.FragmentSearchMovieBinding
import br.com.jera.moviejera.domain.entities.Movie
import br.com.jera.moviejera.presentation.ui.util.setupToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMovieFragment : Fragment() {

    private lateinit var binding: FragmentSearchMovieBinding
    private lateinit var searchView: SearchView
    private val searchAdapter = SearchMovieAdapter { addFavoriteList(it) }
    private val viewModel: SearchMovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSearchMovieBinding.inflate(inflater, container, false)
        setupToolbar(binding.includedToolbar.toolbar, "Buscar")
        setHasOptionsMenu(true)

        setupRecycler()
        subscribeUi()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu.findItem(R.id.search_icon)
        searchView = menuItem.actionView as SearchView
        setupSearchView()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupSearchView() {
        searchView.queryHint = getString(R.string.pesquisar)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.isNotBlank()) {
                    viewModel.searchMovies(newText)
                }
                return false
            }
        })
    }

    private fun setupRecycler() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }

    private fun subscribeUi() {
        viewModel.listMovies.observe(viewLifecycleOwner, Observer {
            searchAdapter.submitList(it)
        })
    }

    private fun addFavoriteList(movie: Movie) {
        viewModel.addFavorite(movie)
    }
}