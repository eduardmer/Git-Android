package com.core_network.service

import com.core_network.model.FollowerModel
import com.core_network.model.GitEventsModel
import com.core_network.model.OrganizationModel
import com.core_network.model.RepositoryModel
import com.core_network.model.SearchRepositoryModel
import com.core_network.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("user")
    suspend fun getAuthenticatedUser(@Header("Authorization") token: String): UserModel

    @GET("users/{USERNAME}")
    suspend fun getUserData(@Header("Authorization") token: String, @Path("USERNAME") username: String): UserModel

    @GET("user/repos")
    suspend fun getReposForAuthenticatedUser(
        @Header("Authorization") token: String,
        @Query("sort") sort: String = "updated_at",
        @Query("per_page") itemsPerPage: Int = 5,
        @Query("page") page: Int = 1
    ): List<RepositoryModel>

    @GET("/users/{USERNAME}/repos")
    suspend fun getReposForUser(
        @Path("USERNAME") username: String,
        @Query("sort") sort: String = "updated_at",
        @Query("per_page") itemsPerPage: Int = 20,
        @Query("page") page: Int
    ): List<RepositoryModel>

    @GET("repos/{USERNAME}/{REPO}")
    suspend fun getRepo(
        @Header("Authorization") token: String,
        @Path("USERNAME") username: String,
        @Path("REPO") repo: String
    ): RepositoryModel

    @GET("user/starred")
    suspend fun getStarredReposByUser(
        @Header("Authorization") token: String,
        @Query("per_page") itemsPerPage: Int = 50,
        @Query("page") page: Int = 1
    ): List<RepositoryModel>

    @GET("user/orgs")
    suspend fun getOrgs(
        @Header("Authorization") token: String,
        @Query("per_page") itemsPerPage: Int = 50,
        @Query("page") page: Int = 1
    ): List<OrganizationModel>

    @GET("user/followers")
    suspend fun getFollowers(@Header("Authorization") token: String): List<FollowerModel>

    @GET("user/following")
    suspend fun getFollowing(@Header("Authorization") token: String): List<FollowerModel>

    @GET("users/{USERNAME}/received_events")
    suspend fun getEvents(@Header("Authorization") token: String, @Path("USERNAME") path: String): List<GitEventsModel>

    @GET("search/repositories")
    suspend fun searchRepository(
        @Header("Authorization") token: String,
        @Query("q") query: String,
        @Query("per_page") per_page: Int = 20,
        @Query("page") page: Int
    ): SearchRepositoryModel

}