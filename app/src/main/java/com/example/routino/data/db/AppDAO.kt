package com.example.routino.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.routino.data.model.Routin

@Dao
interface AppDAO {

    @Insert
    fun InsertRoutin(routin: Routin)

    @Query("SELECT * FROM Routin")
    fun getAllRoutins() : List<Routin>?
}