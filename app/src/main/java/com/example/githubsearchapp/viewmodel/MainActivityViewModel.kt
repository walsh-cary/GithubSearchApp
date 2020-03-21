package com.example.githubsearchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubsearchapp.model.UserDetailResponse
import com.example.githubsearchapp.model.UserSearchResponse

class MainActivityViewModel(val baseURL: String) : ViewModel() {
    private val userSearchDataSet: MutableLiveData<UserSearchResponse> = MutableLiveData()
    private val userDetailDataSet: MutableLiveData<UserDetailResponse> = MutableLiveData()

    fun getUserDetailDataSet(): LiveData<UserDetailResponse> {
        return userDetailDataSet
    }

    fun getUserSearchDataSet(): LiveData<UserSearchResponse> {
        return userSearchDataSet
    }

    fun getUserSearchData(dataSet: UserSearchResponse) {
        this.userSearchDataSet.value = dataSet
    }

    fun getUserDetailData(dataSet: UserDetailResponse) {
        this.userDetailDataSet.value = dataSet
    }
}