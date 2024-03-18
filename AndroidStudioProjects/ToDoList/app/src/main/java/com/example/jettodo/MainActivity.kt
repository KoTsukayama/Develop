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
import com.example.jettodo.components.EditDialog
import com.example.jettodo.components.TaskList
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
                        StudyReportScreen()
                    }
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
            onClickDelete = { viewModel.deleteTask(it) },
        )

    }
}


@Composable
fun StudyReportScreen() {
    var tabSelected by rememberSaveable { mutableStateOf(Screen.DAY) }
    Column {
        TabRow(
            selectedTabIndex = tabSelected.ordinal,
            backgroundColor = Color.LightGray,
            contentColor = Color.Blue,
        ) {
            Screen.values().map { it.name }.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = tabSelected.ordinal == index,
                    onClick = { tabSelected = Screen.values()[index] }
                )
            }
        }
        when (tabSelected) {
            Screen.DAY -> DayScreen()
            Screen.WEEK -> WeekScreen()
            Screen.MONTH -> MonthScreen()
            Screen.YEAR -> YaerScreen()
        }
    }
}

enum class Screen {
    DAY, WEEK, MONTH, YEAR
}

@Composable
fun DayScreen() {
    MainContent()
}

@Composable
fun WeekScreen() {
    MainContent()
}

@Composable
fun MonthScreen(){
    MainContent()
}

@Composable
fun YaerScreen(){
    MainContent()
}