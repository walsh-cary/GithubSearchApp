package com.example.githubsearchapp.model

data class UserDetailResponse(
    var login: String,
    var email: String,
    var location: String,
    var created_at: String,
    var followers: String,
    var following: String,
    var bio: String,
    var public_repos: Int
)
