package com.core_network.service

import com.core_network.model.FollowerModel
import com.core_network.model.GitEventsModel
import com.core_network.model.RepositoryModel
import com.core_network.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("user")
    suspend fun getUser(@Header("Authorization") token: String): UserModel

    @GET("user/repos")
    suspend fun getReposForUser(
        @Header("Authorization") token: String,
        @Query("sort") sort: String = "updated_at",
        @Query("per_page") itemsPerPage: Int = 5
    ): List<RepositoryModel>

    @GET("user/followers")
    suspend fun getFollowers(@Header("Authorization") token: String): List<FollowerModel>

    @GET("user/following")
    suspend fun getFollowing(@Header("Authorization") token: String): List<FollowerModel>

    @GET("users/{USERNAME}/received_events")
    suspend fun getEvents(@Path("USERNAME") path: String): List<GitEventsModel>

}