package com.gitandroid.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.core_model.User
import com.gitandroid.R
import com.gitandroid.databinding.FragmentProfileBinding
import com.gitandroid.ui.adapter.ReposAdapter
import com.gitandroid.ui.binding.setImage
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
        addMenu(R.menu.profile_fragment_menu) { menuItem ->
            when(menuItem.itemId) {
                R.id.share -> {
                    startActivity(
                        Intent(Intent.ACTION_SEND)
                            .putExtra(Intent.EXTRA_TEXT, "https://github.com/${binding.usernameText.text}")
                            .setType("text/plain")
                    )
                    true
                }
                R.id.settings -> false
                else -> false
            }
        }
        val adapter = ReposAdapter()
        binding.viewPager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 2
            setPadding(0,0,100,0)
            setPageTransformer(MarginPageTransformer(10))
            this.adapter = adapter
        }
        collectFlow(viewModel.profileUiState) {
            when(it) {
                is UiState.Success<*> -> {
                    (it.data as User).let { user ->
                        binding.apply {
                            profileImage.setImage(user.avatar_url)
                            usernameText.text = user.login
                            followersText.text = user.followers.toString()
                            followingText.text = user.following.toString()
                            repositoriesNumberText.text = (user.public_repos + user.total_private_repos).toString()
                            starredNumberText.text = user.starred.toString()
                            organizationsNumberText.text = user.orgs.toString()
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
        binding.apply {
            followersButton.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFollowersFragment("Followers", true))
            }
            followingButton.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFollowersFragment("Following", false))
            }
            reposLayout.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToReposFragment())
            }
            organizationsLayout.setOnClickListener {
                Toast.makeText(requireContext(), "Organizations", Toast.LENGTH_SHORT).show()
            }
            starredLayout.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToStarredReposFragment())
            }
        }
    }

    private fun showProgressBar(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

}