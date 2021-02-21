package com.karexpert.navialifecareassignment.repository


class MealRepository(private var apiHelper: MealApiHelper) {

    suspend fun getMealData() = apiHelper.getMealData()

}