package com.example.githubsearchapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearchapp.R
import com.example.githubsearchapp.model.RepoNumberPoko
import com.example.githubsearchapp.model.UserPoko
import com.example.githubsearchapp.model.UserSearchResponse

class MainActivityUserRecyclerViewAdapter(val clickListener: (UserPoko) -> Unit)
    : RecyclerView.Adapter<MainActivityViewHolder>() {

    var userDataSet: UserSearchResponse? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var repoNumberDataSet: RepoNumberPoko? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder =
        MainActivityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.user_card_view,
                    parent,
                    false
                )
        )

    override fun getItemCount(): Int {
        return userDataSet?.items?.size ?: 0
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        userDataSet?.items?.get(position)?.let {
            holder.onBindUserPoko(it, clickListener)
        }
        repoNumberDataSet?.let {
            holder.onBindRepoNumber(it)
        }
    }

}