package com.example.githubsearchapp.model

import android.content.Context
import android.net.ConnectivityManager
import com.example.githubsearchapp.view.CustomApplication
import com.example.githubsearchapp.viewmodel.MainActivityViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient

class UserRepository(private val mainActivityViewModel: MainActivityViewModel) {
    val cacheMax = (5 * 1024 * 1024).toLong()
    lateinit var cache: Cache

    fun isOnline(): Boolean {
        val connectivityManager: ConnectivityManager =
            CustomApplication.getCustomApp()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
    }

//    fun getUser(baseUrl: String, context: Context) {
//        cache = Cache(context.cacheDir, cacheMax)
//        val okHttpClient = OkHttpClient.Builder()
//            .cache(cache)
//            .addInterceptor() { chain ->
//                var request = chain.request()
//                request = if (isOnline())
//                    request.newBuilder().header(
//                        "MusicAppPlayer-Data-Cache",
//                        "public, max-age=" + 5
//                    ).build()
//                else
//                    request.newBuilder().header(
//                        "MusicAppPlayer-Data-Cache",
//                        "public, only-if-cached, max-stale=" + 60 * 10
//                    ).build()
//                chain.proceed(request)
//            }.build()
//        val network = MainActivityNetwork(mainActivityViewModel)
//        network.initRetrofit(baseUrl, okHttpClient)
//    }
}
