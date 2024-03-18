package com.example.jettodo.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.Delete
import com.example.jettodo.CalList
import com.example.jettodo.MainViewModel

@Composable
fun CalList(
    callists:List<CalList>,
    onClickRow:(CalList) -> Unit,
    onClickDelete:(CalList) -> Unit,
){
    LazyColumn {
        items(callists) { callist ->
            CalListRow(
                calList = callist,
                onClickRow = onClickRow,
                onClickDelete = onClickDelete
            )
        }
    }
}