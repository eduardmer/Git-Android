package com.gitandroid.ui.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.gitandroid.BuildConfig
import com.gitandroid.R
import com.gitandroid.databinding.ActivityLoginBinding
import com.gitandroid.ui.main.MainActivity
import com.gitandroid.utils.collectFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        collectFlow {
            viewModel.uistate.collect {
                when(it) {
                    UiState.LoggedIn -> startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    UiState.LoggedOut -> Log.i("pergjigja", "pergjigja")
                    is UiState.Error -> {
                        showProgressBar(false)
                        Toast.makeText(this@LoginActivity, it.error.message ?: "Error", Toast.LENGTH_SHORT).show()
                    }
                    UiState.Loading -> showProgressBar(true)
                }
            }
        }
        binding.apply {
            termsOfUseText.movementMethod = LinkMovementMethod.getInstance()
            signIn.setOnClickListener {
                CustomTabsIntent.Builder().build().launchUrl(this@LoginActivity, getUrl())
            }
        }
    }

    private fun getUrl(): Uri {
        return Uri.Builder().scheme("https")
            .authority("github.com")
            .appendPath("login")
            .appendPath("oauth")
            .appendPath("authorize")
            .appendQueryParameter("client_id", BuildConfig.CLIENT_ID)
            .appendQueryParameter("redirect_uri", BuildConfig.REDIRECT_URI)
            .appendQueryParameter("scope", "user,repo,gist,notifications,read:org")
            .build()
    }

    private fun showProgressBar(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    override fun onResume() {
        super.onResume()
        intent.data?.getQueryParameter("code")?.let { code ->
            viewModel.login(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, code, BuildConfig.REDIRECT_URI)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.getQueryParameter("code")?.let { code ->
            viewModel.login(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, code, BuildConfig.REDIRECT_URI)
        }
    }

}