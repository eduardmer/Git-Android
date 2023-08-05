package com.core_network.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.core_network.GITHUB_STARTING_PAGE_INDEX
import com.core_network.model.RepositoryModel
import com.core_network.service.UserService
import java.lang.Exception

class StarredReposPagingSource(
    private val token: String,
    private val service: UserService
) : PagingSource<Int, RepositoryModel>() {

    override fun getRefreshKey(state: PagingState<Int, RepositoryModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryModel> {
        return try {
            val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
            val data = service.getStarredReposByUser(
                token = "Bearer $token",
                itemsPerPage = params.loadSize,
                page = position
            )
            LoadResult.Page(
                data,
                if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                if (data.size < params.loadSize) null else position + 1
            )
        } catch (error: Exception) {
            LoadResult.Error(error)
        }
    }

}