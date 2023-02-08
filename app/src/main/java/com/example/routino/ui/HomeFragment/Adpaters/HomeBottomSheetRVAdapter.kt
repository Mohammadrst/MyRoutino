package com.example.routino.ui.HomeFragment.Adpaters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.routino.data.model.ColorBottomSheet
import com.example.routino.databinding.HomeFragmentBottomsheetRvSampleBinding


class HomeBottomSheetRVAdapter(var onItemClicked: OnItemClicked) :
    RecyclerView.Adapter<HomeBottomSheetRVAdapter.MyViewHolder>() {

    var listColor = ArrayList<ColorBottomSheet>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class MyViewHolder(val binding: HomeFragmentBottomsheetRvSampleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(color: ColorBottomSheet, position: Int) {
            binding.bottomsheetRoundedIV.setBackgroundColor(Color.parseColor(color.colorCode))
            itemView.setOnClickListener(View.OnClickListener {
                onItemClicked.OnClicked(color.colorCode)
                checkCheckedItem()
                checkColor(color)
                binding.bottomsheetRoundedIV.setImageResource(com.example.routino.R.drawable.ic_baseline_check_24)
            })
        }

        private fun checkColor(color: ColorBottomSheet) {
            listColor.find {
                it.colorCode == color.colorCode
            }?.apply {
                isChecked = true
            }
        }

        private fun checkCheckedItem() {
            listColor.find {
                it.isChecked
            }?.apply {
                isChecked = false
            }
            notifyDataSetChanged()
        }
    }

    private fun checkHandler(binding: HomeFragmentBottomsheetRvSampleBinding) {
        binding.bottomsheetRoundedIV.setImageResource(com.example.routino.R.drawable.ic_baseline_check_24)
        binding.bottomsheetRoundedIV.setImageBitmap(null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            HomeFragmentBottomsheetRvSampleBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listColor[position], position)
        if (listColor[position].isChecked) {
            holder.binding.bottomsheetRoundedIV.setImageResource(com.example.routino.R.drawable.ic_baseline_check_24)
        } else {
            holder.binding.bottomsheetRoundedIV.setImageBitmap(null)
        }
    }

    override fun getItemCount(): Int {
        return listColor.size
    }

    interface OnItemClicked {
        fun OnClicked(color: String)
    }
}