package com.example.githubsearchapp.model

data class UserDetailResponse(
    var login: String,
    var email: String,
    var location: String,
    var created_at: String,
    var followers: Int,
    var following: Int,
    var bio: String,
    var avatar_url: String
)
