package com.example.githubsearchapp.model

import com.example.githubsearchapp.viewmodel.UserActivityViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserActivityNetwork(val viewModel: UserActivityViewModel) {
    var interceptor: HttpLoggingInterceptor? = null
    var client: OkHttpClient? = null

    fun initUserRetrofit(
        baseUrl: String,
        query: String
    ) {
        interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor!!)
        }.build()

        getApi(baseUrl, client!!).getUserDetails(query)
            .enqueue(
                object : Callback<UserDetailResponse> {
                    override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                        //to do failure
                    }

                    override fun onResponse(
                        call: Call<UserDetailResponse>,
                        response: Response<UserDetailResponse>
                    ) {
                        response.body()?.let { viewModel.getUserDetailData(it) }
                    }
                }
            )

        getApi(baseUrl, client!!).getUserRepos(query)
            .enqueue(
                object : Callback<List<RepoPoko>> {
                    override fun onFailure(call: Call<List<RepoPoko>>, t: Throwable) {
                        //to do failure
                    }

                    override fun onResponse(
                        call: Call<List<RepoPoko>>,
                        response: Response<List<RepoPoko>>
                    ) {
                        response.body()?.let { viewModel.getRepoData(it) }
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
