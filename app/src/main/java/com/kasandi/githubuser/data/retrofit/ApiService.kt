package com.kasandi.githubuser.data.retrofit

import com.kasandi.githubuser.data.response.SearchUser
import com.kasandi.githubuser.data.response.UserDetail
import com.kasandi.githubuser.data.response.UserItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getListUsers(
        @Query("q") name: String
    ): Call<SearchUser>

    @GET("users/{username}")
    fun getDetailUsers(
        @Path("username") username: String
    ): Call<UserDetail>

    @GET("users/{username}/followers")
    fun getListFollowers(
        @Path("username") username: String
    ): Call<List<UserItem>>

    @GET("users/{username}/following")
    fun getListFollowing(
        @Path("username") username: String
    ): Call<List<UserItem>>
}