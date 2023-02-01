package com.example.routino.data.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.routino.data.model.Routin
import com.example.routino.data.repository.home.homeRepository
import com.example.routino.data.repository.home.homeRepositoryImpl

class HomeViewModel(private var homeRepository: homeRepository) : ViewModel() {

    fun getCurrrentWeekDays(): ArrayList<Int> {
        return homeRepository.getCurrrentWeekDays()
    }

    fun getFirstDayOfWeek(): Array<Int?> {
       return homeRepository.getFirstDayOfWeek()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getPreviousWeekDays(): ArrayList<Int> {
        return homeRepository.getPreviousWeekDays()
    }

    fun getCurrentDay(): Int {
        return homeRepository.getCurrentDay()
    }

    fun getdaysTitle(): ArrayList<String> {
        return homeRepository.getdaysTitle()
    }

    fun InsertRoutin(routin: Routin) {
        homeRepository.InsertRoutin(routin)
    }

    fun getAllRoutins(): List<Routin> {
        return homeRepository.getAllRoutins()
    }


}