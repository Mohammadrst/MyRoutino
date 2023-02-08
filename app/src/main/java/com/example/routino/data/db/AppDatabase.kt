package com.example.routino.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.routino.data.model.MyConverter
import com.example.routino.data.model.Routin

@Database(entities = [Routin::class], exportSchema = true, version = 1)
@TypeConverters(MyConverter::class)
abstract class AppDatabase :RoomDatabase(){
    abstract fun AppDAO() : AppDAO
}