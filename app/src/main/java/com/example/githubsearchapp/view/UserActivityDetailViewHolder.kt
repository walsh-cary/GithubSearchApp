package com.example.githubsearchapp.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.githubsearchapp.R
import com.example.githubsearchapp.model.UserDetailResponse
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserActivityDetailViewHolder(val itemView: View) {
    val userName: TextView = itemView.findViewById(R.id.tv_user_name_detail)
    val email: TextView = itemView.findViewById(R.id.tv_email_detail)
    val location: TextView = itemView.findViewById(R.id.tv_location_detail)
    val joinDate: TextView = itemView.findViewById(R.id.tv_join_date_detail)
    val followers: TextView = itemView.findViewById(R.id.tv_followers_detail)
    val following: TextView = itemView.findViewById(R.id.tv_following_detail)
    val userBio: TextView = itemView.findViewById(R.id.tv_biography_detail)
    val avatar: ImageView = itemView.findViewById(R.id.iv_user_enlarged)

    fun onBind(data: UserDetailResponse) {
        userName.text = data.login
        if (data.email != null)
            email.text = data.email
        else
            email.text = "No email set"
        if (data.location != null)
            location.text = data.location
        else
            location.text = "No location set"
        followers.text = "Followers: " + data.followers.toString()
        following.text = "Following: " + data.following.toString()
        if (data.bio != null)
            userBio.text = data.bio
        else
            userBio.text = "User has not set a bio"
        Glide.with(itemView).load(data.avatar_url).into(avatar)

        var rawDate = data.created_at.substring(0, 10)
        var parseDate = LocalDate.parse(
            rawDate,
            DateTimeFormatter.ISO_DATE
        )
        var formatDate = parseDate.format(DateTimeFormatter.ofPattern("MMMM dd, YYYY"))

        joinDate.text = formatDate.toString()
    }
}