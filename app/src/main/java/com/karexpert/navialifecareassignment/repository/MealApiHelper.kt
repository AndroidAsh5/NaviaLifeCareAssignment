package com.karexpert.navialifecareassignment.repository

import com.karexpert.navialifecareassignment.networking.ApiService

class MealApiHelper(val apiService: ApiService)  {

    suspend fun getMealData() = apiService.getMealData()
}