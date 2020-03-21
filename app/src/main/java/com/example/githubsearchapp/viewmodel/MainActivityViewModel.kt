package com.example.githubsearchapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubsearchapp.model.UserDetailResponse

class MainActivityViewModel(val baseURL: String) : ViewModel() {
    private val userDataSet: MutableLiveData<UserDetailResponse> = MutableLiveData()
}