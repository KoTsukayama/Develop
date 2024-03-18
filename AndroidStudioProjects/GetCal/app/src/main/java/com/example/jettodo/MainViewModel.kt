package com.example.jettodo

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.Month
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val taskDao: TaskDao,
) : ViewModel() {
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var calorie by mutableStateOf(0)

    var isShowDialog by mutableStateOf(false)
    var tabSelected by mutableStateOf(Screen.CALORIE)

    private var editingTask: Task? = null

    // New or Update task
    val isEditing: Boolean
        get() = editingTask != null


    // Day task
    fun setEditingTask(
        task: Task,
    ) {
        editingTask = task
        title = task.title
        description = task.description
        calorie = task.calorie
    }

    // Task data
    val tasks = taskDao.loadAllTask().distinctUntilChanged()

    // DAY
    fun createTask() {

        // insertTask is suspend
        viewModelScope.launch {
            val newTask = Task(title = title, description = description, calorie = calorie)
            taskDao.insertTask(newTask)
            Log.d(MainViewModel::class.simpleName, "success create task")
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.deleteTask(task)
            Log.d(MainViewModel::class.simpleName, "success delete task")
        }
    }

    fun updateTask() {
        editingTask?.let { task ->
            viewModelScope.launch {
                task.title = title
                task.description = description
                task.calorie = calorie
                taskDao.updateTask(task)
            }
        }
    }

    fun resetProperties() {
        editingTask = null
        title = ""
        description = ""
        calorie = 0
    }
}