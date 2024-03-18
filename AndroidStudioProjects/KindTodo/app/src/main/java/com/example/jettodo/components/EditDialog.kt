package com.example.jettodo.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettodo.MainViewModel
import com.example.jettodo.Screen

@Composable
fun EditDialog(viewModel: MainViewModel = hiltViewModel(), ){
    DisposableEffect(Unit)
    {
        // Called when the dialog is closed
        onDispose {
            if (viewModel.tabSelected == Screen.DAY) {
                viewModel.resetProperties()
            }else if (viewModel.tabSelected == Screen.WEEK) {
                viewModel.resetPropertiesWeek()
            }else if (viewModel.tabSelected == Screen.MONTH) {
                viewModel.resetPropertiesMonth()
            }else if (viewModel.tabSelected == Screen.YEAR) {
                viewModel.resetPropertiesYear()
            }
        }
    }
    AlertDialog(
        // Tap outside the screen
        onDismissRequest = {viewModel.isShowDialog = false},
        title = { Text(text =
        if(
            (viewModel.isEditing && viewModel.tabSelected == Screen.DAY) ||
            (viewModel.isEditingWeek && viewModel.tabSelected == Screen.WEEK) ||
            (viewModel.isEditingMonth && viewModel.tabSelected == Screen.MONTH)||
            (viewModel.isEditingYear&& viewModel.tabSelected == Screen.YEAR)
        ){
            GetEditText("update")
        } else { GetEditText("create") })},
        text = {
               Column {
                   Text(text = "Title")
                   TextField(
                       value = viewModel.title,
                       onValueChange = {viewModel.title = it}
                   )
                   Text(text = "Detail")
                   TextField(
                       value = viewModel.description,
                       onValueChange = {viewModel.description = it}
                   )
               }
        },
        buttons = {
                  Row(
                      modifier = Modifier.padding(8.dp),
                      horizontalArrangement = Arrangement.Center
                  ) {
                      Spacer(modifier = Modifier.weight(1f))
                      Button(
                          modifier = Modifier.width(120.dp),
                          onClick = {viewModel.isShowDialog = false}
                      ){
                          Text(text = "Cancel")
                      }
                      Spacer(modifier = Modifier.weight(1f))

                      Button(
                          modifier = Modifier.width(120.dp),
                          onClick = {
                              viewModel.isShowDialog = false
                              if (viewModel.tabSelected == Screen.DAY) {
                                  if (viewModel.isEditing) {
                                      viewModel.updateTask()
                                  } else {
                                      viewModel.createTask()
                                  }
                              }else if (viewModel.tabSelected == Screen.WEEK) {
                                  if (viewModel.isEditingWeek) {
                                      viewModel.updateWeekTask()
                                  } else {
                                      viewModel.createWeekTask()
                                  }
                              }else if (viewModel.tabSelected == Screen.MONTH) {
                                  if (viewModel.isEditingMonth) {
                                      viewModel.updateMonthTask()
                                  } else {
                                      viewModel.createMonthTask()
                                  }
                              }else if (viewModel.tabSelected == Screen.YEAR) {
                                  if (viewModel.isEditingYear) {
                                      viewModel.updateYearTask()
                                  } else {
                                      viewModel.createYearTask()
                                  }
                              }
                          }
                      ){
                          Text(text = if (
                              (viewModel.isEditing && viewModel.tabSelected == Screen.DAY) ||
                              (viewModel.isEditingWeek && viewModel.tabSelected == Screen.WEEK) ||
                              (viewModel.isEditingMonth && viewModel.tabSelected == Screen.MONTH)||
                              (viewModel.isEditingYear&& viewModel.tabSelected == Screen.YEAR)
                          ) "Update" else "Create")
                      }
                  }
        },
    )
}

@Composable
fun GetEditText(
    kind : String,
    viewModel: MainViewModel = hiltViewModel()): String{
    var text : String
    if (viewModel.tabSelected == Screen.DAY) {
        text = "Day task"
    } else if (viewModel.tabSelected == Screen.WEEK) {
        text = "Week task"
    } else if (viewModel.tabSelected == Screen.MONTH) {
        text = "Month task"
    } else {
        text = "Year task"
    }
    return text + " " + kind
}