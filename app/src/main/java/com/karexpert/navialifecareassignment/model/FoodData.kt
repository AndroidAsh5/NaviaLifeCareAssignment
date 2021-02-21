package com.karexpert.navialifecareassignment.model

import com.google.gson.annotations.SerializedName

data class FoodData(

    @SerializedName("dayOfWeek")
    var dayOfWeek : String,
    @SerializedName("food")
    val food : String,
    @SerializedName("meal_time")
    var meal_time : String
)