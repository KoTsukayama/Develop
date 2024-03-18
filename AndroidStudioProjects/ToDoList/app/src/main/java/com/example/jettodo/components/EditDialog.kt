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

@Composable
fun EditDialog(viewModel: MainViewModel = hiltViewModel(), ){
    DisposableEffect(Unit)
    {
        // Called when the dialog is closed
        onDispose {
            viewModel.resetProperties()
        }
    }
    AlertDialog(
        // Tap outside the screen
        onDismissRequest = {viewModel.isShowDialog = false},
        title = { Text(text = if(viewModel.isEditing)"Task update" else "Task create")},
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
                              if(viewModel.isEditing){
                                  viewModel.updateTask()
                              } else {
                                  viewModel.createTask()
                              }
                          }
                      ){
                          Text(text = if(viewModel.isEditing)"Update" else "Create")
                      }
                  }
        },
    )
}
