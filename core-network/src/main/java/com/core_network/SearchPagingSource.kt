package com.core_network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.core_network.model.RepositoryItem
import com.core_network.service.UserService
import java.lang.Exception
import javax.inject.Inject

class SearchPagingSource @Inject constructor(private val service: UserService)
    : PagingSource<Int, RepositoryItem>() {

    companion object {
        const val GITHUB_STARTING_PAGE_INDEX = 1
        const val INITIAL_LOAD_SIZE = 60
        const val LOAD_SIZE = 20
    }

    override fun getRefreshKey(state: PagingState<Int, RepositoryItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryItem> {
        return try {
            val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
            val data = service.searchRepository("", "android-paging", params.loadSize, position).items
            LoadResult.Page(
                data = data,
                if (position == GITHUB_STARTING_PAGE_INDEX) null else position-1,
                if (data.isEmpty()) null else position+1
            )
        } catch (error: Exception) {
            LoadResult.Error(error)
        }
    }

}