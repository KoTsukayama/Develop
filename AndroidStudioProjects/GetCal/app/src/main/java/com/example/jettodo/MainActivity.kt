package com.example.jettodo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jettodo.components.*
import com.example.jettodo.ui.theme.JetToDoTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.DayOfWeek
import java.time.Year

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
                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.Top,
                    ) {
                        // Tab
                        TabScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun TabScreen(viewModel: MainViewModel = hiltViewModel()) {

    Column {
        TabRow(
            selectedTabIndex = viewModel.tabSelected.ordinal,
            backgroundColor = Color.LightGray,
            contentColor = Color.Red,
            modifier = Modifier.align(androidx.compose.ui.Alignment.Start,)
        ) {
            Screen.values().map { it.name }.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = "LIST") },
                    selected = viewModel.tabSelected.ordinal == index,
                    onClick = { viewModel.tabSelected = Screen.values()[index] },
                    unselectedContentColor = Color.Blue
                )
            }
        }
        MainContent()
    }
}

enum class Screen {
    CALORIE
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent(viewModel: MainViewModel = hiltViewModel()) {

    if (viewModel.isShowDialog) {
        EditDialog()
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.isShowDialog = true }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Create new")
        }

    })
    {
        val tasks by viewModel.tasks.collectAsState(initial = emptyList())

        if (viewModel.tabSelected == Screen.CALORIE) {
            TaskList(
                tasks = tasks,
                onClickRow = {
                    viewModel.setEditingTask(it)
                    viewModel.isShowDialog = true
                },
                onClickDelete = { viewModel.deleteTask(it) },
            )
        }
    }
}


