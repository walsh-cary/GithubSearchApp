package com.example.githubsearchapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearchapp.R
import com.example.githubsearchapp.model.RepoPoko

class UserActivityAdapter :
    RecyclerView.Adapter<UserActivityRecyclerViewHolder>() {

    var repoDataSet: List<RepoPoko>? = null
    var updateDataSet: List<RepoPoko>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setData(data: List<RepoPoko>) {
        repoDataSet = data as MutableList<RepoPoko>;
        updateDataSet = data
        notifyDataSetChanged()
    }

    fun applyFilter(query: String) {
        var tempList = mutableListOf<RepoPoko>()
        for (item in repoDataSet!!) {
            val text = item.name
            if (text.contains(query, true)) {
                tempList.add(item)
            }
        }
        updateDataSet = tempList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserActivityRecyclerViewHolder =
        UserActivityRecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.repo_card_view,
                    parent,
                    false
                )
        )

    override fun getItemCount(): Int {
        return repoDataSet?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserActivityRecyclerViewHolder, position: Int) {
        repoDataSet?.get(position)?.let {
            holder.onBind(it)
        }
    }
}