package com.example.routino.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.routino.data.model.Routin

@Dao
interface AppDAO {

    @Insert
    fun InsertRoutin(routin: Routin)

    @Query("SELECT * FROM Routin")
    fun getAllRoutins() : LiveData<List<Routin>>

    @Update
    fun updateRoutine(routin: Routin)
}