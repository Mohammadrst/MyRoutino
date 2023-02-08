package com.example.routino.data.repository.home

import androidx.lifecycle.LiveData
import com.example.routino.data.model.Routin

interface homeRepository {

    fun getCurrrentWeekDays(): ArrayList<Int>

    fun getFirstDayOfWeek(): Array<Int?>

    fun getPreviousWeekDays(): ArrayList<Int>

    fun getCurrentDay(): Int

    fun getdaysTitle(): ArrayList<String>

    fun InsertRoutin(routin: Routin)

    fun getAllRoutins():LiveData<List<Routin>>

    fun updateRoutineTitle(routin: Routin)

    fun updateRoutineDoneDays(routin: Routin,arrayList: ArrayList<String>)

    fun updateRoutineColor(routin: Routin,color : String)

    fun updateRoutineRemmeberTime(routin: Routin,time : String)

    fun updateRoutineRemmeberDay(routin: Routin,day : String)

    fun updateRoutineTimeInWeek(routin: Routin)
}