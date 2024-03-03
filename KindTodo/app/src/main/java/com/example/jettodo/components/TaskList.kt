package com.example.jettodo.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.jettodo.MonthTask
import com.example.jettodo.Task
import com.example.jettodo.WeekTask
import com.example.jettodo.YearTask

@Composable
fun TaskList(
    tasks:List<Task>,
    onClickRow:(Task) -> Unit,
    onClickDelete:(Task) -> Unit,
){
    LazyColumn {
        items(tasks) { task ->
            TaskRow(
                task = task,
                onClickRow = onClickRow,
                onClickDelete = onClickDelete)
        }
    }
}
@Composable
fun WeekTaskList(
    tasks:List<WeekTask>,
    onClickRow:(WeekTask) -> Unit,
    onClickDelete:(WeekTask) -> Unit,
){
    LazyColumn {
        items(tasks) { WeekTask ->
            WeekTaskRow(
                task = WeekTask,
                onClickRow = onClickRow,
                onClickDelete = onClickDelete)
        }
    }
}

@Composable
fun MonthTaskList(
    tasks:List<MonthTask>,
    onClickRow:(MonthTask) -> Unit,
    onClickDelete:(MonthTask) -> Unit,
){
    LazyColumn {
        items(tasks) { MonthTask ->
            MonthTaskRow(
                task = MonthTask,
                onClickRow = onClickRow,
                onClickDelete = onClickDelete)
        }
    }
}

@Composable
fun YearTaskList(
    tasks:List<YearTask>,
    onClickRow:(YearTask) -> Unit,
    onClickDelete:(YearTask) -> Unit,
){
    LazyColumn {
        items(tasks) { YearTask ->
            YearTaskRow(
                task = YearTask,
                onClickRow = onClickRow,
                onClickDelete = onClickDelete)
        }
    }
}
