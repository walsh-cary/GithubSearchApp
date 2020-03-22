package com.example.githubsearchapp.model

import android.util.Log
import com.example.githubsearchapp.viewmodel.MainActivityViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityNetwork(val viewModel: MainActivityViewModel) {

    var interceptor: HttpLoggingInterceptor? = null
    var client: OkHttpClient? = null

    fun initSearchRetrofit(
        baseUrl: String,
        query: String
    ) {
        interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor!!)
        }.build()

        getApi(baseUrl, client!!).getUsers(query)
            .enqueue(
                object : Callback<UserSearchResponse> {
                    override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {
                        //to do failure
                    }

                    override fun onResponse(
                        call: Call<UserSearchResponse>,
                        response: Response<UserSearchResponse>
                    ) {
                        response.body()?.let { viewModel.getUserSearchData(it) }
                    }
                }
            )
    }

    fun initRepoNumberRetrofit(
        baseUrl: String,
        userName: String
    ) {
        interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor!!)
        }.build()

        getApi(baseUrl, client!!).getRepoNumber(userName)
            .enqueue(
                object : Callback<RepoNumberPoko> {
                    override fun onFailure(call: Call<RepoNumberPoko>, t: Throwable) {
                        //to do failure
                    }

                    override fun onResponse(
                        call: Call<RepoNumberPoko>,
                        response: Response<RepoNumberPoko>
                    ) {
                        response.body()?.let { viewModel.getUserRepoData(it) }
                    }
                }
            )
    }

    fun getApi(url: String, okHttpClient: OkHttpClient): GithubUserApi {

        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url).client(okHttpClient)
            .build().create(GithubUserApi::class.java)
    }
}