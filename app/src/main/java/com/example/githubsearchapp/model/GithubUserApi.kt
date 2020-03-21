package com.example.githubsearchapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUserApi {
    @GET("search/users?")
    fun getUsers(@Query("q") q: String): Call<UserSearchResponse>

    @GET("users/{userName}")
    fun getUserDetails(@Path("userName") userName: String): Call<UserDetailResponse>

    @GET("users/{userName}/repos")
    fun getUserRepos(@Path("userName") userName: String): Call<UserRepoResponse>
}