package com.example.jettodo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CalList(

    // DataEntity is an object for operating sqlite
    // Table structure
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var description: String,
    var calorie:String
)
