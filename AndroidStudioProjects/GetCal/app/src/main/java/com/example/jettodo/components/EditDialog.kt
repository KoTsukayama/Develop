package com.example.jettodo.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettodo.MainViewModel
import com.example.jettodo.Screen

@Composable
fun EditDialog(viewModel: MainViewModel = hiltViewModel()) {
    DisposableEffect(Unit)
    {
        // Called when the dialog is closed
        onDispose {
            viewModel.resetProperties()
        }
    }
    AlertDialog(
        // Tap outside the screen
        onDismissRequest = { viewModel.isShowDialog = false },
        title = {
            Text(
                text =
                if (viewModel.isEditing) {
                    GetEditText("update")
                } else {
                    GetEditText("create")
                }
            )
        },
        text = {
            Column {
                Text(text = "Title")
                TextField(
                    value = viewModel.title,
                    onValueChange = { viewModel.title = it }
                )
                Text(text = "Detail")
                TextField(
                    value = viewModel.description,
                    onValueChange = { viewModel.description = it }
                )
                Text(text = "Calory")
//                TextField(
//                    value = viewModel.calorie.toString(),
//                    onValueChange = { viewModel.calorie = 0 },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                )
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
                    onClick = { viewModel.isShowDialog = false }
                ) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.weight(1f))

                Button(
                    modifier = Modifier.width(120.dp),
                    onClick = {
                        viewModel.isShowDialog = false
                        if (viewModel.isEditing && viewModel.tabSelected == Screen.CALORIE) {
                            viewModel.updateTask()
                        } else {
                            if (viewModel.tabSelected == Screen.CALORIE) {
                                viewModel.createTask()
                            }
                        }
                    }
                ) {
                    Text(text = if (viewModel.isEditing) "Update" else "Create")
                }
            }
        },
    )
}

@Composable
fun GetEditText(
    kind: String,
    viewModel: MainViewModel = hiltViewModel()
): String {
    var text: String
    if (viewModel.tabSelected == Screen.CALORIE) {
        text = "Day task"
    } else {
        text = "Day task"
    }
    return text + " " + kind
}