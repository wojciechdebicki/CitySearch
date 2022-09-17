package com.wojdeb.citysearch.feature.search.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wojdeb.citysearch.R
import com.wojdeb.citysearch.common.observe
import com.wojdeb.citysearch.common.viewBinding
import com.wojdeb.citysearch.databinding.FragmentMainBinding
import com.wojdeb.citysearch.feature.search.SearchViewModel
import com.wojdeb.citysearch.feature.search.State
import com.wojdeb.citysearch.feature.search.domain.Location
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel by viewModels<SearchViewModel>()

    private val locationAdapter = LocationAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.locations.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = locationAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        viewModel.myUiState.observe(lifecycleScope) {
            when (it) {
                is State.Init -> handleInit()
                is State.Loading -> handleLoading()
                is State.Fetched -> handleFetched(it.items)
            }
        }

        binding.search.setOnClickListener {
            viewModel.getLocations(binding.searchInput.text.toString())
        }
    }

    private fun handleInit() {
        binding.progress.visibility = View.GONE
        binding.locations.visibility = View.GONE
    }

    private fun handleFetched(items: List<Location>) {
        binding.progress.visibility = View.GONE
        binding.locations.visibility = View.VISIBLE
        locationAdapter.submitList(items)
    }

    private fun handleLoading() {
        binding.locations.visibility = View.GONE
        binding.progress.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}