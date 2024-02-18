package com.example.jettodo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettodo.components.EditDialog
import com.example.jettodo.components.TaskList
import com.example.jettodo.ui.theme.JetToDoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetToDoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent(viewModel:MainViewModel = hiltViewModel()) {
    if (viewModel.isShowDialog){
        EditDialog()
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {viewModel.isShowDialog = true}){
            Icon(imageVector = Icons.Default.Add, contentDescription = "Create new")
        }
    }) {
        val tasks by viewModel.tasks.collectAsState(initial = emptyList())

        TaskList(
            tasks = tasks,
            onClickRow = {
                viewModel.setEditingTask(it)
                    viewModel.isShowDialog = true
            },
            onClickDelete = {viewModel.deleteTask(it)},
        )
    }
}
