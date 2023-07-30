package com.gitandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gitandroid.databinding.FragmentReposBinding
import com.gitandroid.ui.adapter.LoadStateAdapter
import com.gitandroid.ui.adapter.RepositoryAdapter
import com.gitandroid.utils.collectFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReposFragment : Fragment() {

    private lateinit var binding: FragmentReposBinding
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RepositoryAdapter()
        val footerAdapter = LoadStateAdapter{ adapter.retry() }
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter.withLoadStateFooter(footerAdapter)
        }
        collectFlow(viewModel.repos) {
            adapter.submitData(it)
        }
    }

}