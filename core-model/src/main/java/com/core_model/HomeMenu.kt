package com.core_model

import com.core_domain.R

enum class HomeMenu(val id: Int, val imageId: Int, val descriptionId: Int, val colorId: Int) {

    Issues(0, R.drawable.ic_repository, R.string.issues, R.color.yellow),
    PullRequest(1, R.drawable.ic_repository, R.string.pull_requests, R.color.yellow),
    Discussions(2, R.drawable.ic_repository, R.string.discussions, R.color.yellow),
    Projects(3, R.drawable.ic_repository, R.string.projects, R.color.yellow),
    Repositories(4, R.drawable.ic_repository, R.string.repositories, R.color.dark_blue),
    Organizations(5,R.drawable.ic_repository, R.string.organizations, R.color.orange),
    Starred(6, R.drawable.ic_repository, R.string.starred_menu, R.color.yellow),

}