package com.example.routino.data.repository.home

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.routino.data.db.AppDAO
import com.example.routino.data.model.Routin
import com.example.routino.data.model.TimeWeek
import com.example.routino.uitls.Constants.CHAHAR
import com.example.routino.uitls.Constants.DOSH
import com.example.routino.uitls.Constants.JMOA
import com.example.routino.uitls.Constants.PANJ
import com.example.routino.uitls.Constants.SESHANBE
import com.example.routino.uitls.Constants.SHANBE
import com.example.routino.uitls.Constants.YEK
import saman.zamani.persiandate.PersianDate
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class homeRepositoryImpl(val dao: AppDAO) : homeRepository {

    override fun getCurrrentWeekDays(): ArrayList<TimeWeek> {
        val pDate = PersianDate()
        val dayName = pDate.dayName()
        val dayNumber = pDate.shDay
        val week = calculateDayInWeek(dayName, dayNumber)
        return week
    }

    private fun calculateDayInWeek(dayName: String?, dayNumber: Int): ArrayList<TimeWeek> {
        return when (dayName) {
            SHANBE -> {
                arrayListOf(
                    TimeWeek(dayName, dayNumber, true),
                    TimeWeek(YEK, dayNumber.plus(1), false),
                    TimeWeek(DOSH, dayNumber.plus(2), false),
                    TimeWeek(SESHANBE, dayNumber.plus(3), false),
                    TimeWeek(CHAHAR, dayNumber.plus(4), false),
                    TimeWeek(PANJ, dayNumber.plus(5), false),
                    TimeWeek(JMOA, dayNumber.plus(6), false)
                )
            }
            YEK -> {
                arrayListOf(
                    TimeWeek(SHANBE, dayNumber, false),
                    TimeWeek(dayName, dayNumber.plus(1), true),
                    TimeWeek(DOSH, dayNumber.plus(2), false),
                    TimeWeek(SESHANBE, dayNumber.plus(3), false),
                    TimeWeek(CHAHAR, dayNumber.plus(4), false),
                    TimeWeek(PANJ, dayNumber.plus(5), false),
                    TimeWeek(JMOA, dayNumber.plus(6), false)
                )
            }
            DOSH -> {
                arrayListOf(
                    TimeWeek(SHANBE, dayNumber, false),
                    TimeWeek(YEK, dayNumber.plus(1), false),
                    TimeWeek(dayName, dayNumber.plus(2), true),
                    TimeWeek(SESHANBE, dayNumber.plus(3), false),
                    TimeWeek(CHAHAR, dayNumber.plus(4), false),
                    TimeWeek(PANJ, dayNumber.plus(5), false),
                    TimeWeek(JMOA, dayNumber.plus(6), false)
                )
            }
            SESHANBE -> {
                arrayListOf(
                    TimeWeek(SHANBE, dayNumber, false),
                    TimeWeek(YEK, dayNumber.plus(1), false),
                    TimeWeek(DOSH, dayNumber.plus(2), false),
                    TimeWeek(dayName, dayNumber.plus(3), true),
                    TimeWeek(CHAHAR, dayNumber.plus(4), false),
                    TimeWeek(PANJ, dayNumber.plus(5), false),
                    TimeWeek(JMOA, dayNumber.plus(6), false)
                )
            }
            CHAHAR -> {
                arrayListOf(
                    TimeWeek(SHANBE, dayNumber, false),
                    TimeWeek(YEK, dayNumber.plus(1), false),
                    TimeWeek(DOSH, dayNumber.plus(2), false),
                    TimeWeek(SESHANBE, dayNumber.plus(3), false),
                    TimeWeek(dayName, dayNumber.plus(4), true),
                    TimeWeek(PANJ, dayNumber.plus(5), false),
                    TimeWeek(JMOA, dayNumber.plus(6), false)
                )
            }
            PANJ -> {
                arrayListOf(
                    TimeWeek(SHANBE, dayNumber, false),
                    TimeWeek(YEK, dayNumber.plus(1), false),
                    TimeWeek(DOSH, dayNumber.plus(2), false),
                    TimeWeek(SESHANBE, dayNumber.plus(3), false),
                    TimeWeek(CHAHAR, dayNumber.plus(4), false),
                    TimeWeek(dayName, dayNumber.plus(5), true),
                    TimeWeek(JMOA, dayNumber.plus(6), false)
                )
            }
            else -> {
                arrayListOf(
                    TimeWeek(SHANBE, dayNumber, false),
                    TimeWeek(YEK, dayNumber.plus(1), false),
                    TimeWeek(DOSH, dayNumber.plus(2), false),
                    TimeWeek(SESHANBE, dayNumber.plus(3), false),
                    TimeWeek(CHAHAR, dayNumber.plus(4), false),
                    TimeWeek(PANJ, dayNumber.plus(5), false),
                    TimeWeek(dayName.toString(), dayNumber.plus(6), true)
                )
            }
        }
    }

    override fun getFirstDayOfWeek(): Array<Int?> {
        val list = arrayOfNulls<Int>(3)
        val format: DateFormat = SimpleDateFormat("yyyy/MM/dd")
        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.SATURDAY
        calendar[Calendar.DAY_OF_WEEK] = Calendar.SATURDAY

        val days = arrayOfNulls<String>(7)
        for (i in 0..6) {
            days[i] = format.format(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        var day = days[0]?.split("/")
        var Data =
            PersianDate().gregorian_to_jalali(day!![0].toInt(), day[1].toInt(), day[2].toInt())
        list[0] = Data[0]
        list[1] = Data[1]
        list[2] = Data[2]
        return list
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getPreviousWeekDays(): ArrayList<Int> {
        val list = getFirstDayOfWeek()
        val date = LocalDate.of(list[0]!!, list[1]!!, list[2]!!)
        val mlist = ArrayList<Int>()
        val finalList = ArrayList<Int>()

        for (i in 7 downTo 1) {
            val dateMinus7Days = date.minusDays(i.toLong())
            val formattedDate = dateMinus7Days.format(DateTimeFormatter.ISO_LOCAL_DATE)
            var data = formattedDate.split("-").get(2)
            mlist.add(data.toInt())
        }
        for (i in 6 downTo 0) {
            finalList.add(mlist[i])
        }
        return finalList
    }

    override fun getCurrentDay(): Int {
        return PersianDate().shDay
    }

    override fun getdaysTitle(): ArrayList<String> {
        var list = ArrayList<String>()
        var mlist = ArrayList<String>()
        list.add("ش")
        list.add("ی")
        list.add("د")
        list.add("س")
        list.add("چ")
        list.add("پ")
        list.add("ج")
        for (i in 6 downTo 0) {
            mlist.add(list[i])
        }
        return mlist
    }

    override fun InsertRoutin(routin: Routin) {
        dao.InsertRoutin(routin)
    }

    override fun getAllRoutins(): List<Routin> {
        return dao.getAllRoutins()!!
    }
}