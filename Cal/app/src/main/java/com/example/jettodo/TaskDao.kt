package com.example.jettodo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface CalListDao {

    // suspend is an asynchronous process

    // Defined by annotation
    @Insert
    suspend fun insertTask(callist: CalList)

    @Query("SELECT * FROM CalList")
    fun  loadAllTask(): Flow<List<CalList>>

    @Update
    suspend fun updateTask(callist: CalList)

    @Delete
    suspend fun deleteTask(callist: CalList)
}