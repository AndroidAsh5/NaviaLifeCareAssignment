package com.karexpert.navialifecareassignment

import com.karexpert.navialifecareassignment.core.Constants
import com.karexpert.navialifecareassignment.networking.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private var logging = HttpLoggingInterceptor()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpLogClient())
                .build()
    }

    private fun getHttpLogClient(): OkHttpClient {
        val client = OkHttpClient.Builder().build()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.networkInterceptors
        return client
    }
    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}