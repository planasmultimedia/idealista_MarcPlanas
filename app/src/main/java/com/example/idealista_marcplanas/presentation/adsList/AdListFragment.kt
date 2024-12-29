package com.example.idealista_marcplanas.presentation.adsList

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.idealista_marcplanas.R
import com.example.idealista_marcplanas.utils.Response
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdListFragment : Fragment(R.layout.fragment_list){

    private val viewModel: ListViewModel by viewModels()
    private lateinit var adapter: AdAdapter
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)

        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())

        adapter = AdAdapter { ad -> }

        recyclerView.adapter = adapter

        observeAdsState()
    }

    private fun observeAdsState() {
        lifecycleScope.launch {
            viewModel.adsState.collectLatest { state ->
                when (state) {
                    is Response.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is Response.Success -> {
                        progressBar.visibility = View.GONE
                        adapter.submitList(state.data)
                    }
                    is Response.Error -> {
                        progressBar.visibility = View.GONE
                        showErrorDialog(state.message)
                    }
                }
            }
        }
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("Retry") { dialog, _ ->
                viewModel.retry()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}