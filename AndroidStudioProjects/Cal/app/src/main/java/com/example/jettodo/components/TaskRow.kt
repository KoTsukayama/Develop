package com.example.jettodo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Delete
import com.example.jettodo.CalList

@Composable
fun CalListRow(
    calList: CalList,
    onClickRow: (CalList) -> Unit,
    onClickDelete: (CalList) -> Unit,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onClickRow(calList) },
        elevation = 5.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            /*.clickable { onClickRow(task) }*/
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = calList.calorie,
                fontWeight = FontWeight.Bold,
                color = if(darkTheme) {
                    Color.Magenta
                }else{
                    Color.Red
                },
                fontSize = 25.sp,
                fontStyle = FontStyle.Italic,
            )
            Text(
                text = " kcal",
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = calList.title,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 17.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Monospace,
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onClickDelete(calList) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }


}

@Preview
@Composable
fun TaskRowPreview() {
    CalListRow(
        calList = CalList(title = "preview", description = "", calorie = ""),
        onClickRow = {},
        onClickDelete = {},
    )
}