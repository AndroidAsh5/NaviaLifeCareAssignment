package com.karexpert.navialifecareassignment.networking

import com.karexpert.navialifecareassignment.model.MealData
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {


    @GET("/dummy/")
    suspend fun getMealData(): Response<MealData>


}