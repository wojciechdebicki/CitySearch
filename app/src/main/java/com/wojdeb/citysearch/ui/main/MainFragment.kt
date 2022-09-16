package com.wojdeb.citysearch.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.wojdeb.citysearch.R
import com.wojdeb.citysearch.common.viewBinding
import com.wojdeb.citysearch.databinding.FragmentMainBinding
import com.wojdeb.citysearch.ui.main.domain.Location
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        Toast.makeText(
            context, "Fetched " + items.first().cityName, Toast.LENGTH_LONG
        ).show()
    }

    private fun handleLoading() {
        //TODO
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}