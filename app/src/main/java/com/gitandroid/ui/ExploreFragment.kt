package com.gitandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.core_model.GitEvents
import com.gitandroid.databinding.FragmentExploreBinding
import com.gitandroid.ui.adapter.EventAdapter
import com.gitandroid.utils.collectFlow
import com.gitandroid.utils.showProgressBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private lateinit var binding: FragmentExploreBinding
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = EventAdapter()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }
        collectFlow {
            viewModel.events.collect {
                when(it) {
                    is UiState.Success<*> -> {
                        adapter.submitList(it.data as List<GitEvents>)
                        binding.progressBar.showProgressBar(false)
                    }
                    is UiState.Error -> {
                        Toast.makeText(requireContext(), it.error.message ?: "Error", Toast.LENGTH_SHORT).show()
                        binding.progressBar.showProgressBar(false)
                    }
                    UiState.Loading -> binding.progressBar.showProgressBar(true)
                }
            }
        }
    }

}