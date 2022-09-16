package com.wojdeb.citysearch.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wojdeb.citysearch.R
import com.wojdeb.citysearch.common.viewBinding
import com.wojdeb.citysearch.databinding.FragmentMainBinding
import com.wojdeb.citysearch.ui.main.domain.Location
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    private val locationAdapter = LocationAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.locations.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = locationAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is State.Init -> {}
                is State.Loading -> handleLoading()
                is State.Fetched -> handleFetched(it.items)
            }
        }

        viewModel.getLocations("san fran")
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
        fun newInstance() = MainFragment()
    }
}