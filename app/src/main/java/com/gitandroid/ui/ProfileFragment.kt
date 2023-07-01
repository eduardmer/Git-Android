package com.gitandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.core_model.User
import com.gitandroid.R
import com.gitandroid.databinding.FragmentProfileBinding
import com.gitandroid.ui.adapter.ReposAdapter
import com.gitandroid.utils.addMenu
import com.gitandroid.utils.collectFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMenu(R.menu.profile_fragment_menu, findNavController())
        val adapter = ReposAdapter()
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPager.adapter = adapter
        collectFlow {
            viewModel.profileUiState.collect {
                when(it) {
                    is UiState.Success<*> -> {
                        (it.data as User).let { user ->
                            binding.apply {
                                profileImage.load(user.avatar_url)
                                usernameText.text = user.login
                                followersText.text = user.followers.toString()
                                followingText.text = user.following.toString()
                                repositoriesNumberText.text = (user.public_repos + user.total_private_repos).toString()
                            }
                            adapter.submitList(user.repos)
                            showProgressBar(false)
                        }
                    }
                    is UiState.Error -> {
                        Toast.makeText(requireContext(), it.error.message ?: "Error", Toast.LENGTH_SHORT).show()
                        showProgressBar(false)
                    }
                    UiState.Loading -> showProgressBar(true)
                }
            }
        }
        binding.textView5.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFollowersFragment("Followers", true))
        }
        binding.textView7.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFollowersFragment("Following", false))
        }
        binding.reposLayout.setOnClickListener {
            Toast.makeText(requireContext(), "Repos", Toast.LENGTH_SHORT).show()
        }
        binding.organizationsLayout.setOnClickListener {
            Toast.makeText(requireContext(), "Organizations", Toast.LENGTH_SHORT).show()
        }
        binding.starredLayout.setOnClickListener {
            Toast.makeText(requireContext(), "Starred", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showProgressBar(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

}