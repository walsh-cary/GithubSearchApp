package com.example.githubsearchapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearchapp.R
import com.example.githubsearchapp.model.RepoNumberPoko
import com.example.githubsearchapp.model.UserPoko
import com.example.githubsearchapp.model.UserSearchResponse
import com.example.githubsearchapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mainActivityViewModel: MainActivityViewModel? = null

    companion object {
        val USER_LOGIN_INTENT_ID = "USER_LOGIN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userRecyclerViewAdapter =
            MainActivityUserRecyclerViewAdapter { user: UserPoko -> onUserClick(user) }

        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.adapter = userRecyclerViewAdapter

        mainActivityViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return MainActivityViewModel() as T
                }
            }
        ).get(MainActivityViewModel::class.java)

        mainActivityViewModel!!.getUserSearchDataSet().observe(
            this@MainActivity,
            object : Observer<UserSearchResponse> {
                override fun onChanged(t: UserSearchResponse?) {
                    userRecyclerViewAdapter.userDataSet = t
                }
            }
        )
        mainActivityViewModel!!.getRepoNumberDataSet().observe(
            this@MainActivity,
            object : Observer<RepoNumberPoko> {
                override fun onChanged(t: RepoNumberPoko?) {
                    userRecyclerViewAdapter.repoNumberDataSet = t
                }
            }
        )
        userRecyclerViewAdapter.userDataSet = mainActivityViewModel!!.getUserSearchDataSet().value
        userRecyclerViewAdapter.repoNumberDataSet =
            mainActivityViewModel!!.getRepoNumberDataSet().value

        btn_search.setOnClickListener {
            mainActivityViewModel!!.initMainRetrofit(et_user_search.text.toString())
        }
    }

    private fun onUserClick(user: UserPoko) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra(USER_LOGIN_INTENT_ID, user.login)
        startActivity(intent)
    }
}
