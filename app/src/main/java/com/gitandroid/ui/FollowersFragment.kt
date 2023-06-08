package com.gitandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.core_model.Follower
import com.gitandroid.databinding.FragmentFollowersBinding
import com.gitandroid.ui.adapter.FollowersAdapter
import com.gitandroid.utils.collectFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowersFragment : Fragment() {

    private lateinit var binding: FragmentFollowersBinding
    private val args: FollowersFragmentArgs by navArgs()
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FollowersAdapter()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }
        val flow = if (args.getFollowers)
            viewModel.followers
        else viewModel.following
        collectFlow {
            flow.collect {
                when(it) {
                    is UiState.Success<*> -> {
                        adapter.submitList(it.data as List<Follower>)
                        showProgressBar(false)
                    }
                    is UiState.Error -> {
                        Toast.makeText(requireContext(), it.error.message ?: "Error", Toast.LENGTH_SHORT).show()
                        showProgressBar(false)
                    }
                    UiState.Loading -> showProgressBar(true)
                }
            }
        }
    }

    private fun showProgressBar(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

}