package com.example.jettodo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    // suspend is an asynchronous process
    // Defined by annotation
   @Insert
    suspend fun insertTask(task: Task)
    @Query("SELECT * FROM Task")
    fun  loadAllTask(): Flow<List<Task>>
    @Update
    suspend fun updateTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)
}

@Dao
interface WeekTaskDao {
    @Insert
    suspend fun insertTask(task: WeekTask)
    @Query("SELECT * FROM WeekTask")
    fun  loadAllTask(): Flow<List<WeekTask>>
    @Update
    suspend fun updateTask(task: WeekTask)
    @Delete
    suspend fun deleteTask(task: WeekTask)
}

@Dao
interface MonthTaskDao {
    @Insert
    suspend fun insertTask(task: MonthTask)
    @Query("SELECT * FROM MonthTask")
    fun  loadAllTask(): Flow<List<MonthTask>>
    @Update
    suspend fun updateTask(task: MonthTask)
    @Delete
    suspend fun deleteTask(task: MonthTask)
}

@Dao
interface YearTaskDao {
    @Insert
    suspend fun insertTask(task: YearTask)
    @Query("SELECT * FROM YearTask")
    fun  loadAllTask(): Flow<List<YearTask>>
    @Update
    suspend fun updateTask(task: YearTask)
    @Delete
    suspend fun deleteTask(task: YearTask)
}