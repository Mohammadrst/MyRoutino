package com.example.routino.ui.HomeFragment.Adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.routino.databinding.HomeFragmentMainRrvSampleBinding

class MainRecyclerViewItemAdapter : RecyclerView.Adapter<MainRecyclerViewItemAdapter.MyViewHolder>(){

    inner class MyViewHolder(var binding : HomeFragmentMainRrvSampleBinding ) : RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(HomeFragmentMainRrvSampleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}