package com.example.routino.ui.HomeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.routino.databinding.HomeFragmentMainRrvSampleBinding
import com.example.routino.databinding.HomeFragmentSubRvSampleBinding

class SubRecyclerViewItemAdapter : RecyclerView.Adapter<SubRecyclerViewItemAdapter.MyViewHolder>(){

    inner class MyViewHolder(var binding : HomeFragmentSubRvSampleBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(HomeFragmentSubRvSampleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}