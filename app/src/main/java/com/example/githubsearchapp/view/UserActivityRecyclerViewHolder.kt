package com.example.githubsearchapp.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearchapp.R
import com.example.githubsearchapp.model.RepoPoko

class UserActivityRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.tv_repo_name)
    val forksCount: TextView = itemView.findViewById(R.id.tv_forks)
    val stargazerCount: TextView = itemView.findViewById(R.id.tv_stars)

    fun onBind(repoPoko: RepoPoko) {
        name.text = repoPoko.name
        forksCount.text = repoPoko.forks_count.toString()
        stargazerCount.text = repoPoko.stargazers_count.toString()
    }
}