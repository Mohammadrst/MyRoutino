package com.example.routino.ui.HomeFragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.routino.databinding.HomeFragmentRvSampleBinding
import kotlin.properties.Delegates

class CalanderRecyclerViewAdapter :
    RecyclerView.Adapter<CalanderRecyclerViewAdapter.MyViewHolder>() {

    var days = ArrayList<Int>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var dayTitle = ArrayList<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var currentDay = ArrayList<Int>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MyViewHolder(var binding: HomeFragmentRvSampleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(day: Int, title: String) {
            if (day == currentDay[0]) {
                binding.dayName.setTextColor(Color.BLUE)
                binding.dayNumber.setTextColor(Color.BLUE)
            }
            binding.dayName.text = title
            binding.dayNumber.text = day.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            HomeFragmentRvSampleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(days[position], dayTitle[position])
    }

    override fun getItemCount(): Int {
        return days.size
    }
}