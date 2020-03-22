package com.example.githubsearchapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearchapp.R
import com.example.githubsearchapp.model.RepoPoko
import com.example.githubsearchapp.model.UserDetailResponse
import com.example.githubsearchapp.viewmodel.UserActivityViewModel
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    var userActivityViewModel: UserActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val userLogin = intent.getStringExtra(MainActivity.USER_LOGIN_INTENT_ID)
        val userInfoViewHolder = UserActivityDetailViewHolder(findViewById(R.id.cv_user_detail))

        val reposAdapter = UserActivityAdapter()
        rv_repo_selection.layoutManager = LinearLayoutManager(this)
        rv_repo_selection.adapter = reposAdapter

        userActivityViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return UserActivityViewModel() as T
                }
            }
        ).get(UserActivityViewModel::class.java)

        userActivityViewModel!!.getUserDetailDataSet().observe(
            this,
            object : Observer<UserDetailResponse> {
                override fun onChanged(t: UserDetailResponse?) {
                    userInfoViewHolder.onBind(t!!)
                }
            }
        )

        userActivityViewModel!!.getRepoDataSet().observe(
            this,
            object : Observer<List<RepoPoko>> {
                override fun onChanged(t: List<RepoPoko>?) {
                    reposAdapter.repoDataSet = t
                }
            }
        )

        userActivityViewModel!!.initUserNetwork(userLogin)
    }
}
