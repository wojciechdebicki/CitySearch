package com.wojdeb.citysearch.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.wojdeb.citysearch.R
import com.wojdeb.citysearch.common.viewBinding
import com.wojdeb.citysearch.databinding.FragmentMainBinding
import com.wojdeb.citysearch.networking.GeoNamesService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.message.text = viewModel.testMethod()


        val retrofit = Retrofit.Builder().baseUrl("https://secure.geonames.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service: GeoNamesService = retrofit.create(GeoNamesService::class.java)

        lifecycleScope.launch {
            val query = service.listGeonames("san fran")
            Toast.makeText(
                context,
                "Fetched: " + query.totalResultsCount + " " + query.geonames.size,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}