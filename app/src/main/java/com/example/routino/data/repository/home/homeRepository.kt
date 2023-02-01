package com.example.routino.data.repository.home

import com.example.routino.data.model.Routin
import com.example.routino.data.model.TimeWeek

interface homeRepository {

    fun getCurrrentWeekDays(): ArrayList<TimeWeek>

    fun getFirstDayOfWeek(): Array<Int?>

    fun getPreviousWeekDays(): ArrayList<Int>

    fun getCurrentDay(): Int

    fun getdaysTitle(): ArrayList<String>

    fun InsertRoutin(routin: Routin)

    fun getAllRoutins():List<Routin>
}