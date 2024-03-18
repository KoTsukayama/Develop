package com.example.jettodo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CalList::class], version = 1, exportSchema = false)
abstract class AppDatabaseCalList : RoomDatabase() {
    abstract  fun callistDao(): CalListDao
}