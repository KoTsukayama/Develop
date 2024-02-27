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