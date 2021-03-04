package com.karexpert.navialifecareassignment.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karexpert.navialifecareassignment.R
import com.karexpert.navialifecareassignment.RetrofitBuilder
import com.karexpert.navialifecareassignment.adapter.MealAdapter
import com.karexpert.navialifecareassignment.broadcastreceiver.MealBroadcastReceiver
import com.karexpert.navialifecareassignment.core.Utills
import com.karexpert.navialifecareassignment.databinding.ActivityMainBinding
import com.karexpert.navialifecareassignment.model.FoodData
import com.karexpert.navialifecareassignment.repository.MealApiHelper
import com.karexpert.navialifecareassignment.viewmodel.MealViewModel
import com.karexpert.navialifecareassignment.viewmodel.MealViewModelFactory
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MealViewModel
    lateinit var mealAdapter: MealAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Log.e("aa","aa)
        setupUI()
        setupViewModel()
        setupObservers()
    }

    private fun setupUI() {

        binding.
