package com.gitandroid.utils

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.ui.onNavDestinationSelected

fun Fragment.addMenu(
    menuId: Int,
    navController: NavController,
    state: Lifecycle.State = Lifecycle.State.RESUMED
) {
    requireActivity().addMenuProvider(object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(menuId, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return menuItem.onNavDestinationSelected(navController)
        }

    }, viewLifecycleOwner, state)
}