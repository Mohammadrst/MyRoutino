package com.example.routino.ui.HomeFragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.routino.R
import com.example.routino.data.model.Routin
import com.example.routino.databinding.FragmentHomeBinding
import com.example.routino.ui.HomeFragment.Adpaters.CalanderRecyclerViewAdapter
import com.example.routino.ui.HomeFragment.Adpaters.MainRecyclerViewItemAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class HomeFragment : Fragment(),HomeAddRoutinBottomSheetFragment.OnSaveBtnClicked,MainRecyclerViewItemAdapter.OndaysChanged {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var TAG = "Response"
    private var currentDay = 0
    var previousWeek = ArrayList<Int>()
    var currentWeek = ArrayList<Int>()
    var weekTitles = ArrayList<String>()

    var calanderRecyclerViewAdapter = CalanderRecyclerViewAdapter()
    lateinit var mainRecyclerViewItemAdapter : MainRecyclerViewItemAdapter

    private val homeViewModel : HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainRecyclerViewItemAdapter = MainRecyclerViewItemAdapter(this,requireActivity())

        getValues()
        SetupCalenderRecyclerView(currentWeek, weekTitles, currentDay)
        ClickHandler()

        homeViewModel.getAllRoutins().observe(viewLifecycleOwner, Observer {
            SetupMainRecyclerview(it)
            if (it.isNotEmpty()){
                Log.i(TAG, "onViewCreated: " + it[0].doneDaysList.size)
            }
        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getValues(){
        weekTitles = homeViewModel.getdaysTitle()
        currentWeek = homeViewModel.getCurrrentWeekDays()
        currentDay = homeViewModel.getCurrentDay()
        previousWeek = homeViewModel.getPreviousWeekDays()
    }

    private fun SetupCalenderRecyclerView(days : ArrayList<Int>, titles : ArrayList<String>, current : Int){

        var currentDay = ArrayList<Int>()
        currentDay.add(current)
        calanderRecyclerViewAdapter.days =days
        calanderRecyclerViewAdapter.dayTitle =titles
        calanderRecyclerViewAdapter.currentDay = currentDay
        binding.homeFragmentWeekRecyclerView.layoutManager = GridLayoutManager(requireContext(),7)
        binding.homeFragmentWeekRecyclerView.adapter = calanderRecyclerViewAdapter
    }

    private fun SetupMainRecyclerview(list : List<Routin>){

        var arrayList = ArrayList<Routin>(list)
        mainRecyclerViewItemAdapter.list = arrayList
        binding.homeFragmentMainRV.adapter = mainRecyclerViewItemAdapter
        binding.homeFragmentMainRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun ClickHandler(){

        binding.homeFragmentWeekBtn.setOnClickListener(View.OnClickListener {
            if(binding.homeFragmentWeekBtn.text == "هفته قبل"){
                binding.homeFragmentWeekBtn.text = "هفته بعد"
                SetupCalenderRecyclerView(previousWeek, weekTitles, currentDay)
            }else{
                binding.homeFragmentWeekBtn.text = "هفته قبل"
                SetupCalenderRecyclerView(currentWeek, weekTitles, currentDay)
            }
        })

        binding.homeFragmentAddIV.setOnClickListener(View.OnClickListener {
            val addDialog = HomeAddRoutinBottomSheetFragment(this)
            addDialog.show(parentFragmentManager,"")
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
                mainRecyclerViewItemAdapter.notifyItemMoved(initial, final)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }
        })
    }

    override fun OnSaveClicked(routin: Routin) {
        homeViewModel.InsertRoutin(routin)
    }

    override fun OnDayAdd(routin: Routin, day: Int) {
        /*var list = ArrayList<String>()
        list.add(day.toString())
        routin.doneDaysList = list*/
        routin.doneDaysList.add("1")
//        homeViewModel.updateRoutineTitle(routin)
    }

    override fun OnDayRemove(routin: Routin, day: Int) {
        //homeViewModel.updateRoutineTitle(routin)
    }

}