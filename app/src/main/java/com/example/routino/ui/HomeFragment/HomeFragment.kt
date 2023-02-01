package com.example.routino.ui.HomeFragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.routino.data.viewmodel.HomeViewModel
import com.example.routino.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var currentDay = 0
    var previousWeek = ArrayList<Int>()
    var currentWeek = ArrayList<Int>()
    var weekTitles = ArrayList<String>()

    var adapter = CalanderRecyclerViewAdapter()

    private val homeViewModel :HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weekTitles = homeViewModel.getdaysTitle()
        currentWeek = homeViewModel.getCurrrentWeekDays()
        currentDay = homeViewModel.getCurrentDay()
        previousWeek = homeViewModel.getPreviousWeekDays()
        setupRecyclerView(currentWeek, weekTitles, currentDay)

        ClickHandler()

    }

    private fun setupRecyclerView( days : ArrayList<Int>,titles : ArrayList<String>,current : Int){

        var currentDay = ArrayList<Int>()
        currentDay.add(current)
        adapter.days =days
        adapter.dayTitle =titles
        adapter.currentDay = currentDay
        binding.homeFragmentWeekRecyclerView.layoutManager = GridLayoutManager(requireContext(),7)
        binding.homeFragmentWeekRecyclerView.adapter = adapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun ClickHandler(){

        binding.homeFragmentWeekBtn.setOnClickListener(View.OnClickListener {
            if(binding.homeFragmentWeekBtn.text == "هفته قبل"){
                binding.homeFragmentWeekBtn.text = "هفته بعد"
                setupRecyclerView(previousWeek, weekTitles, currentDay)
            }else{
                binding.homeFragmentWeekBtn.text = "هفته قبل"
                setupRecyclerView(currentWeek, weekTitles, currentDay)
            }
        })


    }

    private fun ItemMoveHandler(){
        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN or ItemTouchHelper.UP, 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                val initial = viewHolder.adapterPosition
                val final = target.adapterPosition
                //Collections.swap(list, initial, final)
                adapter.notifyItemMoved(initial, final)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }
        })
    }

}