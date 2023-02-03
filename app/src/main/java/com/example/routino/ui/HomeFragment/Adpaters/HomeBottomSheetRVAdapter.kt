package com.example.routino.ui.HomeFragment.Adpaters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.routino.databinding.HomeFragmentBottomsheetRvSampleBinding

class HomeBottomSheetRVAdapter(var onItemClicked: OnItemClicked) : RecyclerView.Adapter<HomeBottomSheetRVAdapter.MyViewHolder>() {

    var list = ArrayList<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class MyViewHolder(val binding: HomeFragmentBottomsheetRvSampleBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(color : String){
                binding.bottomsheetRoundedIV.setBackgroundColor(Color.parseColor(color))
                itemView.setOnClickListener(View.OnClickListener {
                    onItemClicked.OnClicked(color)
                })
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(HomeFragmentBottomsheetRvSampleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClicked{
        fun OnClicked(color : String)
    }
}