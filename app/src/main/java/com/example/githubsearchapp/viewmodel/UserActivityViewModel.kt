package com.example.githubsearchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubsearchapp.model.RepoPoko
import com.example.githubsearchapp.model.UserActivityNetwork
import com.example.githubsearchapp.model.UserDetailResponse

class UserActivityViewModel : ViewModel() {
    private val userDetailResponseDataSet: MutableLiveData<UserDetailResponse> = MutableLiveData()
    private val repoPokoDataSet: MutableLiveData<List<RepoPoko>> = MutableLiveData()

    fun getUserDetailDataSet(): LiveData<UserDetailResponse> {
        return userDetailResponseDataSet
    }

    fun getRepoDataSet(): LiveData<List<RepoPoko>> {
        return repoPokoDataSet
    }

    fun getUserDetailData(dataSet: UserDetailResponse) {
        this.userDetailResponseDataSet.value = dataSet
    }

    fun getRepoData(dataSet: List<RepoPoko>) {
        this.repoPokoDataSet.value = dataSet
    }

    fun initUserNetwork(query: String) {
        val network = UserActivityNetwork(this)
        network.initUserRetrofit("https://api.github.com/", query)
    }
}