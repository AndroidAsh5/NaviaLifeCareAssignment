package com.karexpert.navialifecareassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.karexpert.navialifecareassignment.core.Resource
import com.karexpert.navialifecareassignment.model.MealData
import com.karexpert.navialifecareassignment.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class MealViewModel(private val apiRepository: MealRepository) : ViewModel() {

    val data : LiveData<Resource<Response<MealData>>> = liveData(Dispatchers.IO) {
        val retrievedData = apiRepository.getMealData()
        try {
            emit(Resource.success(data = retrievedData))
        }catch (e:Exception){
            emit(Resource.error(msg = e.message ?: "Error Occurred!",data = null))
        }
    }



}