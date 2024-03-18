package com.example.jettodo

import android.util.Log
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val callistdao: CalListDao) :ViewModel() {
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var calorie by mutableStateOf("")
    var isShowDialog by mutableStateOf(false)

    private var editingCalList:CalList?= null

    // New or Update callist
    val isEditing:Boolean
        get() = editingCalList != null

    fun setEditingCalList(callist: CalList){
        editingCalList = callist
        title = callist.title
        description = callist.description
        calorie = callist.calorie
    }

    // CalList data
    val callists = callistdao.loadAllTask().distinctUntilChanged()

    fun createTask(){
        // insertList is suspend
        viewModelScope.launch {
            val newTask = CalList(title = title, description = description, calorie = calorie)
            callistdao.insertTask(newTask)
            Log.d(MainViewModel::class.simpleName, "success create callist")
        }
    }

    fun deleteTask (callist: CalList){
        viewModelScope.launch {
            callistdao.deleteTask(callist)
            Log.d(MainViewModel::class.simpleName, "success delete callistdao")
        }
    }

    fun updateTask(){
        editingCalList?.let{ callist ->
            viewModelScope.launch {
                callist.title = title
                callist.description = description
                callist.calorie = calorie
                callistdao.updateTask(callist)
            }
        }
    }

    fun resetProperties()
    {
        editingCalList = null
        title = ""
        description = ""
        calorie = ""
    }
}