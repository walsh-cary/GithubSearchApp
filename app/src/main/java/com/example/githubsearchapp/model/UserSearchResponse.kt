package com.example.githubsearchapp.model

data class UserSearchResponse(
    var total_count: Int,
    var items: List<UserPoko>
)

data class UserPoko(
    var login: String,
    var avatar_url: String
)
