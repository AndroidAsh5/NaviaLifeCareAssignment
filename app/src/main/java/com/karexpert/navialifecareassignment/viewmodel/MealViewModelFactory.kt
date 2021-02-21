package com.karexpert.navialifecareassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karexpert.navialifecareassignment.repository.MealApiHelper
import com.karexpert.navialifecareassignment.repository.MealRepository


class MealViewModelFactory(private val apiHelper: MealApiHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            return MealViewModel(MealRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown view model class name")
    }

}