package com.example.routino.data.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.routino.data.model.Routin
import com.example.routino.data.repository.home.homeRepositoryImpl

class homeViewModel(private var homeRepositoryImpl: homeRepositoryImpl) : ViewModel() {

    fun getCurrrentWeekDays(): ArrayList<Int> {
        return homeRepositoryImpl.getCurrrentWeekDays()
    }

    fun getFirstDayOfWeek(): Array<Int?> {
       return homeRepositoryImpl.getFirstDayOfWeek()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getPreviousWeekDays(): ArrayList<Int> {
        return homeRepositoryImpl.getPreviousWeekDays()
    }

    fun getCurrentDay(): Int {
        return homeRepositoryImpl.getCurrentDay()
    }

    fun getdaysTitle(): ArrayList<String> {
        return homeRepositoryImpl.getdaysTitle()
    }

    fun InsertRoutin(routin: Routin) {
        homeRepositoryImpl.InsertRoutin(routin)
    }

    fun getAllRoutins(): List<Routin> {
        return homeRepositoryImpl.getAllRoutins()
    }


}