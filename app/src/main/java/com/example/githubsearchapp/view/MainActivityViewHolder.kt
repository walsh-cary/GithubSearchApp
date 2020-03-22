package com.example.githubsearchapp.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubsearchapp.R
import com.example.githubsearchapp.model.RepoNumberPoko
import com.example.githubsearchapp.model.UserDetailResponse
import com.example.githubsearchapp.model.UserPoko

class MainActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val userName: TextView = itemView.findViewById(R.id.tv_user_name)
    val avatarUrl: ImageView = itemView.findViewById(R.id.iv_user_thumbnail)
    val repoNumber: TextView = itemView.findViewById(R.id.tv_repo_number)

    fun onBindUserPoko(userPoko: UserPoko, clickListener: (UserPoko) -> Unit) {
        userName.text = userPoko.login
        Glide.with(itemView).load(userPoko.avatar_url).into(avatarUrl)
        itemView.setOnClickListener { clickListener(userPoko) }
    }

    fun onBindRepoNumber(repoNumberPoko: RepoNumberPoko) {
        repoNumber.text = (repoNumberPoko.public_repos.toString())
    }
}