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
    private val weekDao: WeekTaskDao,
    private val monthDao: MonthTaskDao,
    private val yearDao: YearTaskDao,
) : ViewModel() {
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var isShowDialog by mutableStateOf(false)

    var tabSelected by mutableStateOf(Screen.DAY)

    private var editingTask: Task? = null
    private var editingWeekTask: WeekTask? = null
    private var editingMonthTask: MonthTask? = null
    private var editingYearTask: YearTask? = null

    // New or Update task
    val isEditing: Boolean
        get() = editingTask != null

    val isEditingWeek: Boolean
        get() = editingWeekTask != null

    val isEditingMonth: Boolean
        get() = editingMonthTask != null

    val isEditingYear: Boolean
        get() = editingYearTask != null

    // Day task
    fun setEditingTask(
        task: Task,
    ) {
        editingTask = task
        title = task.title
        description = task.description
    }

    // Week task
    fun setEditingWeekTask(
        weekTask: WeekTask,
    ) {
        editingWeekTask = weekTask
        title = weekTask.title
        description = weekTask.description
    }

    // Month task
    fun setEditingMonthTask(
        monthTask: MonthTask,
    ) {
        editingMonthTask = monthTask
        title = monthTask.title
        description = monthTask.description
    }

    // Year task
    fun setEditingYearTask(
        yearTask: YearTask,
    ) {
        editingYearTask = yearTask
        title = yearTask.title
        description = yearTask.description
    }

    // Task data
    val tasks = taskDao.loadAllTask().distinctUntilChanged()
    val tasksWeek = weekDao.loadAllTask().distinctUntilChanged()
    val tasksMonth = monthDao.loadAllTask().distinctUntilChanged()
    val tasksYear = yearDao.loadAllTask().distinctUntilChanged()

    // DAY
    fun createTask() {

        // insertTask is suspend
        viewModelScope.launch {
            val newTask = Task(title = title, description = description)
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
                taskDao.updateTask(task)
            }
        }
    }

    // WEEK
    fun createWeekTask() {
        viewModelScope.launch {
            val newTask = WeekTask(title = title, description = description)
            weekDao.insertTask(newTask)
            Log.d(MainViewModel::class.simpleName, "success create task")
        }
    }

    fun deleteWeekTask(task: WeekTask) {
        viewModelScope.launch {
            weekDao.deleteTask(task)
            Log.d(MainViewModel::class.simpleName, "success delete task")
        }
    }

    fun updateWeekTask() {
        editingWeekTask?.let { task ->
            viewModelScope.launch {
                task.title = title
                task.description = description
                weekDao.updateTask(task)
            }
        }
    }

    // MONTH
    fun createMonthTask() {
        viewModelScope.launch {
            val newTask = MonthTask(title = title, description = description)
            monthDao.insertTask(newTask)
            Log.d(MainViewModel::class.simpleName, "success create task")
        }
    }

    fun deleteMonthTask(task: MonthTask) {
        viewModelScope.launch {
            monthDao.deleteTask(task)
            Log.d(MainViewModel::class.simpleName, "success delete task")
        }
    }

    fun updateMonthTask() {
        editingMonthTask?.let { task ->
            viewModelScope.launch {
                task.title = title
                task.description = description
                monthDao.updateTask(task)
            }
        }
    }

    // YEAR
    fun createYearTask() {
        viewModelScope.launch {
            val newTask = YearTask(title = title, description = description)
            yearDao.insertTask(newTask)
            Log.d(MainViewModel::class.simpleName, "success create task")
        }
    }

    fun deleteYearTask(task: YearTask) {
        viewModelScope.launch {
            yearDao.deleteTask(task)
            Log.d(MainViewModel::class.simpleName, "success delete task")
        }
    }

    fun updateYearTask() {
        editingYearTask?.let { task ->
            viewModelScope.launch {
                task.title = title
                task.description = description
                yearDao.updateTask(task)
            }
        }
    }
    fun resetProperties() {
        editingTask = null
        title = ""
        description = ""
    }
}