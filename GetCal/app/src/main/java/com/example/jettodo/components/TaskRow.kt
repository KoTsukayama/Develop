package com.example.jettodo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jettodo.Task

@Composable
fun TaskRow(
    task: Task,
    onClickRow: (Task) -> Unit,
    onClickDelete: (Task) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onClickRow(task) },
        elevation = 5.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                /*.clickable { onClickRow(task) }*/,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(text = task.title)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onClickDelete(task) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
@Preview
@Composable
fun TaskRowPreview(){
    TaskRow(
        task = Task(title = "preview", description = "", calorie = 0),
        onClickRow = {},
    ) {}
}