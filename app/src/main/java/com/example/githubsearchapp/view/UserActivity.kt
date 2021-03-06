package com.example.githubsearchapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearchapp.R
import com.example.githubsearchapp.model.RepoPoko
import com.example.githubsearchapp.model.UserDetailResponse
import com.example.githubsearchapp.viewmodel.UserActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user.*
import kotlin.math.log

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
                    reposAdapter.setData(t!!)
                }
            }
        )

        setUpSearchBar(reposAdapter)

        userActivityViewModel!!.initUserNetwork(userLogin)
    }

    private fun setUpSearchBar(adapter: UserActivityAdapter) {
        et_repo_search.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    adapter.applyFilter(et_repo_search.text.toString())
                }
            }
        )
    }
}
