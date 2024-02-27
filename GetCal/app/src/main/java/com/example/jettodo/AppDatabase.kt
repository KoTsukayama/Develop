package com.example.jettodo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract  fun taskDao(): TaskDao
}

@Database(entities = [WeekTask::class], version = 1, exportSchema = false)
abstract class AppDatabaseWeek : RoomDatabase() {
    abstract  fun weekTaskDao(): WeekTaskDao
}

@Database(entities = [MonthTask::class], version = 1, exportSchema = false)
abstract class AppDatabaseMonth : RoomDatabase() {
    abstract  fun monthTaskDao(): MonthTaskDao
}

@Database(entities = [YearTask::class], version = 1, exportSchema = false)
abstract class AppDatabaseYear : RoomDatabase() {
    abstract  fun yearTaskDao(): YearTaskDao
}