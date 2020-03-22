package com.example.githubsearchapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearchapp.R
import com.example.githubsearchapp.model.RepoNumberPoko
import com.example.githubsearchapp.model.UserDetailResponse
import com.example.githubsearchapp.model.UserSearchResponse
import com.example.githubsearchapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val userRecyclerViewAdapter: UserRecyclerViewAdapter by lazy { UserRecyclerViewAdapter() }

    var mainActivityViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        populateRecyclerView(mainActivityViewModel!!)
        userRecyclerViewAdapter.userDataSet = mainActivityViewModel!!.getUserSearchDataSet().value
        userRecyclerViewAdapter.repoNumberDataSet =
            mainActivityViewModel!!.getRepoNumberDataSet().value

        et_user_search.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    mainActivityViewModel!!.initMainRetrofit(et_user_search.text.toString())
                }

            }
        )
    }

    private fun populateRecyclerView(mainActivityViewModel: MainActivityViewModel) {
        mainActivityViewModel.getUserSearchDataSet().observe(
            this@MainActivity,
            object : Observer<UserSearchResponse> {
                override fun onChanged(t: UserSearchResponse?) {
                    userRecyclerViewAdapter.userDataSet = t
                }
            }
        )
        mainActivityViewModel.getRepoNumberDataSet().observe(
            this@MainActivity,
            object : Observer<RepoNumberPoko> {
                override fun onChanged(t: RepoNumberPoko?) {
                    userRecyclerViewAdapter.repoNumberDataSet = t
                }
            }
        )

    }
}
