package com.example.githubsearchapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubsearchapp.model.*

class MainActivityViewModel() : ViewModel() {
    private val userSearchDataSet: MutableLiveData<UserSearchResponse> = MutableLiveData()
    private val repoNumberDataSet: MutableLiveData<RepoNumberPoko> = MutableLiveData()
    private var userName: String? = null

    fun getRepoNumberDataSet(): LiveData<RepoNumberPoko> {
        return repoNumberDataSet
    }

    fun getUserSearchDataSet(): LiveData<UserSearchResponse> {
        return userSearchDataSet
    }

    fun getUserSearchData(dataSet: UserSearchResponse) {
        this.userSearchDataSet.value = dataSet
    }

    fun getUserRepoData(dataSet: RepoNumberPoko) {
        this.repoNumberDataSet.value = dataSet
    }

    fun initMainRetrofit(query: String) {
        val network = MainActivityNetwork(this)
        network.initSearchRetrofit("https://api.github.com/", query)
    }
}