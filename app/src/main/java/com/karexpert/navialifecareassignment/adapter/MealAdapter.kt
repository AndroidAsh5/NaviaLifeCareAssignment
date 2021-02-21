package com.karexpert.navialifecareassignment.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.karexpert.navialifecareassignment.R
import com.karexpert.navialifecareassignment.databinding.MealRowBinding
import com.karexpert.navialifecareassignment.model.FoodData

class MealAdapter(
    var context: Context,
    private var dataModel: List<FoodData>,
    private val clickListener: (FoodData) -> Unit
) : RecyclerView.Adapter<MealAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemRowBinding: MealRowBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.meal_row, parent, false)
        return MyViewHolder(itemRowBinding)

    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var dataa = dataModel.get(position)
        holder.bind(dataa, clickListener,context)
    }

    class MyViewHolder(var itemRowBinding: MealRowBinding) :
        RecyclerView.ViewHolder(itemRowBinding.root) {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(dataModel: FoodData, clickListener: (FoodData) -> Unit,context: Context) {
            itemRowBinding.model = dataModel
            itemRowBinding.cardLayout.setOnClickListener{
                clickListener(dataModel)
            }

        }

    }


}