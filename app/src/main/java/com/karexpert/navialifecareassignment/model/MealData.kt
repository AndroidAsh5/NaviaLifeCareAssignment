package com.karexpert.navialifecareassignment.model

import com.google.gson.annotations.SerializedName

data class MealData(
    @SerializedName("diet_duration")
    val diet_duration : Long,
    @SerializedName("week_diet_data")
    val week_diet_data : WeekDietData
)