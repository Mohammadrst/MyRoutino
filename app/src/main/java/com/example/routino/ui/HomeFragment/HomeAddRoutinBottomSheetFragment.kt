package com.example.routino.ui.HomeFragment

import android.app.TimePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.routino.R
import com.example.routino.data.model.ColorBottomSheet
import com.example.routino.data.model.Routin
import com.example.routino.databinding.FragmentHomeAddRoutinBottomSheetBinding
import com.example.routino.ui.HomeFragment.Adpaters.HomeBottomSheetRVAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HomeAddRoutinBottomSheetFragment(var onSaveBtnClicked: OnSaveBtnClicked) : BottomSheetDialogFragment(),
    HomeBottomSheetRVAdapter.OnItemClicked {

    private var _binding: FragmentHomeAddRoutinBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var timesInweekList = emptyArray<String>()
    private var title = ""
    private var timesInweek = ""
    private var color = ""
    private val TAG = "Response"
    private var time = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeAddRoutinBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorRVsetup()
        autoCompleteTextViewSetup()
        clickHandler()
        remmemberSetup()
        chipSetup()

    }

    fun colorList(): ArrayList<ColorBottomSheet> {
        val list = ArrayList<ColorBottomSheet>()
        list.add(ColorBottomSheet("#d90000"))
        list.add(ColorBottomSheet("#0452cf"))
        list.add(ColorBottomSheet("#cf04c8"))
        list.add(ColorBottomSheet("#cf044e"))
        list.add(ColorBottomSheet("#04cf55"))
        list.add(ColorBottomSheet("#cf9c04"))
        list.add(ColorBottomSheet("#84cf04"))
        list.add(ColorBottomSheet("#04cfbe"))
        list.add(ColorBottomSheet("#040bcf"))
        list.add(ColorBottomSheet("#cf0404"))
        list.add(ColorBottomSheet("#14dbbd"))
        list.add(ColorBottomSheet("#9e1fed"))
        list.add(ColorBottomSheet("#ed1f7f"))
        list.add(ColorBottomSheet("#78ed1f"))
        list.add(ColorBottomSheet("#1f9eed"))
        list.add(ColorBottomSheet("#bded1f"))
        list.add(ColorBottomSheet("#eda81f"))
        list.add(ColorBottomSheet("#67ed1f"))
        list.add(ColorBottomSheet("#1f7fed"))
        list.add(ColorBottomSheet("#c4eb5b"))
        list.add(ColorBottomSheet("#7c65f0"))
        return list
    }

    fun colorRVsetup() {
        var adapter = HomeBottomSheetRVAdapter(this)
        adapter.listColor = colorList()
        binding.homeBottomSheetColorRV.adapter = adapter
        binding.homeBottomSheetColorRV.layoutManager = GridLayoutManager(requireContext(), 7)
    }

    fun autoCompleteTextViewSetup() {
        timesInweekList = arrayOf("1", "2", "3", "4", "5", "6", "7")
        val arrayAdapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                timesInweekList
            )
        binding.homeBottomSheetAutoCompleteTV.setAdapter(arrayAdapter)
        binding.homeBottomSheetAutoCompleteTV.setOnClickListener(View.OnClickListener {
            binding.homeBottomSheetAutoCompleteTV.showDropDown()
        })
    }

    fun clickHandler() {

        binding.homeBottomSheetAutoCompleteTV.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            timesInweek = timesInweekList[position]
        })

        binding.homeFragmentSaveTV.setOnClickListener(View.OnClickListener {

            title = binding.homeBottomSheetTitleEdt.text.trim().toString()
            if (title == "") {
                Toast.makeText(requireContext(), "لطفاعنوان را مشخص کنید", Toast.LENGTH_SHORT)
                    .show()
            }

            if (timesInweek == "") {
                Toast.makeText(
                    requireContext(),
                    "لطفا تعداد روز در هفته را مشخص کنید",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (color == "") {
                Toast.makeText(requireContext(), "لطفا رنگ را مشخص کنید", Toast.LENGTH_SHORT).show()
            }
            if (color != "" && timesInweek != "" && timesInweek != ""){
                var routin = Routin(title, timesInWeek = timesInweek.toInt(), color = color)
                onSaveBtnClicked.OnSaveClicked(routin)
                dismiss()
            }

        })

    }

    fun remmemberSetup() {
        binding.homeBottomSheetRememberCheckBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                binding.homeBottomSheetDaysLayout.visibility = View.VISIBLE
                binding.homeBottomSheetTimeTV.isEnabled = true
                binding.homeFragmentChangeTimeBtn.isEnabled = true
                getTime()
            }else{
                binding.homeBottomSheetDaysLayout.visibility = View.GONE
                binding.homeBottomSheetTimeTV.isEnabled = false
                binding.homeFragmentChangeTimeBtn.isEnabled = false
            }
        })
    }

    fun getTime(){
            val cal = Calendar.getInstance()

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

             time = SimpleDateFormat("HH:mm").format(cal.time)
             binding.homeBottomSheetTimeTV.text = SimpleDateFormat("HH:mm").format(cal.time)
            }

            binding.homeFragmentChangeTimeBtn.setOnClickListener {
                TimePickerDialog(requireContext(), timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
            }
    }

    fun chipSetup(){

        binding.chipHomeBottomSheetSatherday.setOnClickListener {
            if (binding.chipHomeBottomSheetSatherday.isChecked){
                binding.chipHomeBottomSheetSatherday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.LightBlue))
                binding.chipHomeBottomSheetSatherday.setTextColor(Color.WHITE)
            }else{
                binding.chipHomeBottomSheetSatherday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.Silver))
                binding.chipHomeBottomSheetSatherday.setTextColor(resources.getColor(R.color.Grey))
            }
        }
        binding.chipHomeBottomSheetSunday.setOnClickListener {
            if (binding.chipHomeBottomSheetSunday.isChecked){
                binding.chipHomeBottomSheetSunday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.LightBlue))
                binding.chipHomeBottomSheetSunday.setTextColor(Color.WHITE)
            }else{
                binding.chipHomeBottomSheetSunday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.Silver))
                binding.chipHomeBottomSheetSunday.setTextColor(resources.getColor(R.color.Grey))
            }
        }
        binding.chipHomeBottomSheetMonday.setOnClickListener {
            if (binding.chipHomeBottomSheetMonday.isChecked){
                binding.chipHomeBottomSheetMonday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.LightBlue))
                binding.chipHomeBottomSheetMonday.setTextColor(Color.WHITE)
            }else{
                binding.chipHomeBottomSheetMonday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.Silver))
                binding.chipHomeBottomSheetMonday.setTextColor(resources.getColor(R.color.Grey))
            }
        }
        binding.chipHomeBottomSheetThursday.setOnClickListener {
            if (binding.chipHomeBottomSheetThursday.isChecked){
                binding.chipHomeBottomSheetThursday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.LightBlue))
                binding.chipHomeBottomSheetThursday.setTextColor(Color.WHITE)
            }else{
                binding.chipHomeBottomSheetThursday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.Silver))
                binding.chipHomeBottomSheetThursday.setTextColor(resources.getColor(R.color.Grey))
            }
        }
        binding.chipHomeBottomSheetWednesday.setOnClickListener {
            if (binding.chipHomeBottomSheetWednesday.isChecked){
                binding.chipHomeBottomSheetWednesday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.LightBlue))
                binding.chipHomeBottomSheetWednesday.setTextColor(Color.WHITE)
            }else{
                binding.chipHomeBottomSheetWednesday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.Silver))
                binding.chipHomeBottomSheetWednesday.setTextColor(resources.getColor(R.color.Grey))
            }
        }
        binding.chipHomeBottomSheetThusday.setOnClickListener {
            if (binding.chipHomeBottomSheetThusday.isChecked){
                binding.chipHomeBottomSheetThusday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.LightBlue))
                binding.chipHomeBottomSheetThusday.setTextColor(Color.WHITE)
            }else{
                binding.chipHomeBottomSheetThusday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.Silver))
                binding.chipHomeBottomSheetThusday.setTextColor(resources.getColor(R.color.Grey))
            }
        }
        binding.chipHomeBottomSheetFriday.setOnClickListener {
            if (binding.chipHomeBottomSheetFriday.isChecked){
                binding.chipHomeBottomSheetFriday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.LightBlue))
                binding.chipHomeBottomSheetFriday.setTextColor(Color.WHITE)
            }else{
                binding.chipHomeBottomSheetFriday.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.Silver))
                binding.chipHomeBottomSheetFriday.setTextColor(resources.getColor(R.color.Grey))
            }
        }
    }

    override fun OnClicked(color: String) {
        this.color = color
    }

    interface OnSaveBtnClicked{
        fun OnSaveClicked(routin: Routin)
    }

}