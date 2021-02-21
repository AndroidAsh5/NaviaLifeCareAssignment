package com.karexpert.navialifecareassignment.model

import com.google.gson.annotations.SerializedName

data class WeekDietData(
    @SerializedName("monday")
    val monday : List<FoodData>,
    @SerializedName("wednesday")
    val wednesday : List<FoodData>,
    @SerializedName("thursday")
    val thursday : List<FoodData>



    )