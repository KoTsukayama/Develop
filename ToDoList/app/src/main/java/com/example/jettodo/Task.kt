package com.example.jettodo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(

    // DataEntity is an object for operating sqlite
    // Table structure
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var description: String,
)
