package com.example.routino.ui.HomeFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.routino.databinding.FragmentHomeAddRoutinBottomSheetBinding
import com.example.routino.ui.HomeFragment.Adpaters.HomeBottomSheetRVAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class HomeAddRoutinBottomSheetFragment : BottomSheetDialogFragment(),
    HomeBottomSheetRVAdapter.OnItemClicked {

    private var _binding: FragmentHomeAddRoutinBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var timesInweekList = emptyArray<String>()
    private var title = ""
    private var timesInweek = ""
    private var color = ""
    private val TAG = "Response"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeAddRoutinBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorRVsetup()
        autoCompleteTextViewSetup()
        clickHandler()

    }

    fun colorList(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("#d90000")
        list.add("#0452cf")
        list.add("#cf04c8")
        list.add("#cf044e")
        list.add("#04cf55")
        list.add("#cf9c04")
        list.add("#84cf04")
        list.add("#04cfbe")
        list.add("#040bcf")
        list.add("#cf0404")
        list.add("#14dbbd")
        list.add("#9e1fed")
        list.add("#ed1f7f")
        list.add("#78ed1f")
        list.add("#1f9eed")
        list.add("#bded1f")
        list.add("#eda81f")
        list.add("#67ed1f")
        list.add("#1f7fed")
        list.add("#c4eb5b")
        list.add("#7c65f0")
        return list
    }

    fun colorRVsetup() {
        var adapter = HomeBottomSheetRVAdapter(this)
        adapter.list = colorList()
        binding.homeBottomSheetColorRV.adapter = adapter
        binding.homeBottomSheetColorRV.layoutManager = GridLayoutManager(requireContext(), 7)
    }

    fun autoCompleteTextViewSetup() {
        timesInweekList = arrayOf("1", "2", "3", "4", "5", "6", "7")
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, timesInweekList)
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
                Toast.makeText(requireContext(), "لطفاعنوان را مشخص کنید", Toast.LENGTH_SHORT).show()
            }

            if (timesInweek == "") {
                Toast.makeText(requireContext(), "لطفا تعداد روز در هفته را مشخص کنید", Toast.LENGTH_SHORT).show()
            }

            if (color == "") {
                Toast.makeText(requireContext(), "لطفا رنگ را مشخص کنید", Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun OnClicked(color: String) {
        this.color = color
    }

}