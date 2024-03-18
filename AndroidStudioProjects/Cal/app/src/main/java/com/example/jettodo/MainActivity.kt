package com.example.jettodo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettodo.components.EditDialog
import com.example.jettodo.components.CalList
import com.example.jettodo.ui.theme.JetToDoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?,

        ) {
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
                        CalorieScreen()
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent(
    viewModel: MainViewModel = hiltViewModel(),
    darkTheme: Boolean = isSystemInDarkTheme(),
) {

    if (viewModel.isShowDialog) {
        EditDialog()
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.isShowDialog = true },
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.height(50.dp).fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(
                        vertical = 5.dp,
                        horizontal = 10.dp,
                    )
                ) {
                    Text(
                        text = "ADD MENU",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.W300,
                    )
                }

            }
        }
    ) {
        val callists by viewModel.callists.collectAsState(initial = emptyList())

        // tatal calorie
        var cnt:Int = 0
        callists.forEach { elem ->
            if (!elem.calorie.isNullOrEmpty()){
                var cal = elem.calorie.toIntOrNull()
                if (cal != null){
                    cnt += cal
                }
            }
        }
        var cal = cnt.toString()

        Column(
            modifier = Modifier.padding(
                vertical = 5.dp,
                horizontal = 5.dp,
            )
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Text(
                    text = "Total Calorie",
                    modifier = Modifier.padding(
                        vertical = 0.dp,
                        horizontal = 20.dp,
                    ),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.W300,
                )
                Text(
                    text = cal,
                    modifier = Modifier.padding(
                        vertical = 3.dp,
                        horizontal = 20.dp,
                    ),
                    fontSize = 35.sp,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    lineHeight = TextUnit.Unspecified,
                    color = if(darkTheme) {
                        Color.Cyan
                    }else{
                        Color.Blue
                    }
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        text = "kcal",
                        modifier = Modifier.padding(
                            vertical = 0.dp,
                            horizontal = 0.dp,
                        ),
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.W300,
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            CalList(
                callists = callists,
                onClickRow = {
                    viewModel.setEditingCalList(it)
                    viewModel.isShowDialog = true
                },
                onClickDelete = { viewModel.deleteTask(it) },
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun CalorieScreen() {
    var tabSelected by rememberSaveable { mutableStateOf(Screen.CALST) }
    Column {
        TabRow(
            selectedTabIndex = tabSelected.ordinal,
            backgroundColor = Color.DarkGray,
            contentColor = Color.White,
            modifier = Modifier.height(80.dp)
        ) {
            Screen.values().map { it.name }.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            modifier = Modifier.padding(
                                vertical = 0.dp,
                                horizontal = 20.dp,
                            ),
                            fontSize = 40.sp,
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.W100,
                        )
                    },
                    selected = tabSelected.ordinal == index,
                    onClick = { tabSelected = Screen.values()[index] },
                )
            }
        }
        when (tabSelected) {
            Screen.CALST -> CalScreen()
        }
    }
}

enum class Screen {
    CALST
}

@Composable
fun CalScreen() {
    MainContent()
}