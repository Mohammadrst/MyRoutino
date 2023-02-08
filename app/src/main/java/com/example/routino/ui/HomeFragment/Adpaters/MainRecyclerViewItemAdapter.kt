package com.example.routino.ui.HomeFragment.Adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.routino.R
import com.example.routino.data.model.Routin
import com.example.routino.databinding.HomeFragmentMainRrvSampleBinding

class MainRecyclerViewItemAdapter(var ondaysChanged: OndaysChanged,var ctx :Context) :
    RecyclerView.Adapter<MainRecyclerViewItemAdapter.MyViewHolder>() {

    var list = ArrayList<Routin>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MyViewHolder(var binding: HomeFragmentMainRrvSampleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(routin: Routin) {

            var maxProgress = routin.timesInWeek
            var currentProgress: Int

            binding.homeFragmentMainRvTv.text = routin.title

            if (routin.doneDaysList.isNullOrEmpty()) {
                currentProgress = 0
            } else {
                currentProgress = routin.doneDaysList?.size!!
            }
            daysHandler(binding, routin)

            binding.homeFragmentMainRvProgress.max = maxProgress
            binding.homeFragmentMainRvProgress.progress = currentProgress

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            HomeFragmentMainRrvSampleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun daysHandler(binding: HomeFragmentMainRrvSampleBinding, routin: Routin) {

        itemClickHandler(binding.ivHomeRvFriday, R.string.friday, routin)
        itemClickHandler(binding.ivHomeRvMonday, R.string.monday, routin)
        itemClickHandler(binding.ivHomeRvSunday, R.string.sunday, routin)
        itemClickHandler(binding.ivHomeRvThursday, R.string.thursday, routin)
        itemClickHandler(binding.ivHomeRvThusday, R.string.thusday, routin)
        itemClickHandler(binding.ivHomeRvWensday, R.string.wednesday, routin)
        itemClickHandler(binding.ivHomeRvSatherday, R.string.saturday, routin)
    }


    fun itemClickHandler(iv: ImageView, day: Int, routin: Routin) {

        iv.setOnClickListener {

            if (iv.tag == "close") {
                iv.tag = "check"
                iv.setImageResource(R.drawable.ic_baseline_check_24_dark)
                ondaysChanged.OnDayAdd(routin, day)
            } else if (iv.tag == "check"){
                iv.tag = "close"
                iv.setImageResource(R.drawable.ic_baseline_close_24_dark)
                ondaysChanged.OnDayRemove(routin, day)
            }
        }
    }


    interface OndaysChanged {
        fun OnDayAdd(routin: Routin, day: Int)
        fun OnDayRemove(routin: Routin, day: Int)
    }
}