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
        /////
        -----
        setupUI()
        setupViewModel()
        setupObservers()
    }

    private fun setupUI() {

        binding.recyclerViewMeal.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )

    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
                this, MealViewModelFactory(MealApiHelper(RetrofitBuilder.apiService))
        ).get(MealViewModel::class.java)

    }



    private fun setupObservers() {
        viewModel.data.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Utills.SUCCESS -> {
                        binding.lavMain.visibility = View.GONE
                        var foodData1 = mutableListOf<FoodData>()

                        val apidata1 = resource.data!!.body()!!.week_diet_data.monday
                        val apidata2 = resource.data!!.body()!!.week_diet_data.wednesday
                        val apidata3 = resource.data!!.body()!!.week_diet_data.thursday
                        for (i in 0 until apidata1.size) {
                            apidata1.get(i).dayOfWeek = "Monday"
                            foodData1.add(apidata1.get(i))


                        }

                        for (i in 0 until apidata2.size) {
                            apidata2.get(i).dayOfWeek = "Wednesday"
                            foodData1.add(apidata2.get(i))


                        }
                        for (i in 0 until apidata3.size) {
                            apidata3.get(i).dayOfWeek = "Thursday"
                            foodData1.add(apidata3.get(i))


                        }

                        mealAdapter =
                                MealAdapter(this, foodData1, { selectedItem: FoodData ->
                                    listItemClicked(
                                            selectedItem
                                    )
                                })
                        binding.recyclerViewMeal.adapter = mealAdapter
//                        val foodData2 = mutableListOf<FoodData>()
//                        foodData2.add()
//                        foodData1.get(0).dayOfWeek


                        for (i in 0 until foodData1.size) {
                            val customCalendar = Calendar.getInstance()
                            var dateTime = foodData1.get(i).meal_time.split(":")
                            val hour = dateTime[0]
                            val minute = dateTime[1]
                            var nextDay: Int = 0
                            val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
                            Log.e("aaa",currentDay.toString())
                            if (foodData1.get(i).dayOfWeek == "Monday") {
                                if(currentDay!=2) {
                                    nextDay =
                                            LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).dayOfMonth
                                }else{
                                    nextDay=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                                }
                            } else if (foodData1.get(i).dayOfWeek == "Wednesday") {
                               if(currentDay!=4){
                                   nextDay =
                                           LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)).dayOfMonth

                               }else{
                                   nextDay=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

                               }

                            } else if (foodData1.get(i).dayOfWeek == "Thursday") {
                               if(currentDay!=5){
                                   nextDay =
                                           LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY)).dayOfMonth

                               }else{
                                   nextDay=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

                               }

                            }

                            customCalendar.set(
                                    Calendar.getInstance().get(Calendar.YEAR), Calendar.MONTH - 1, nextDay, hour.toInt(), minute.toInt(), 0
                            )

                            var fiveMinsBefore = customCalendar.timeInMillis - 300000
                            if (customCalendar.timeInMillis>System.currentTimeMillis()){
                                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                                val intent = Intent(this, MealBroadcastReceiver::class.java)
                                val isWorking = PendingIntent.getBroadcast(this@MainActivity, i, intent, PendingIntent.FLAG_NO_CREATE) != null
                                if (!isWorking) {
                                    intent.putExtra("mealName", foodData1.get(i).food)
                                    val pendingIntent = PendingIntent.getBroadcast(this, i, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, fiveMinsBefore, pendingIntent)

                                }

                            }

                        }
                        Toast.makeText(this, "Meal Alert Created for all meals", Toast.LENGTH_LONG).show()


                    }
                    Utills.ERROR -> {
                        binding.lavMain.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

                    }

                }
            }


        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun listItemClicked(foodModel: FoodData) {
        Toast.makeText(this, "Reminder Already Set for : ${foodModel.food}", Toast.LENGTH_SHORT).show()


    }


}
