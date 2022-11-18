package com.example.genshioow

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.genshioow.ui.theme.PurpleBackground
import com.example.genshioow.ui.theme.PurpleText
import java.time.LocalDate


@Composable
fun MainScreen(
    navController: NavController,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {

        val dayOfWeek = LocalDate.now().dayOfWeek.name
        var selectDay = ""
        val day = remember { mutableStateOf(dayOfWeek) }
        val openDialog = remember { mutableStateOf(false) }

        Text(
            text = "Today's Ascension Materials",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "[ ${day.value} ]",
            color = Color.LightGray,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.clickable(
                onClick = {
                    openDialog.value = true
                }
            )
        )
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                modifier = Modifier.padding(10.dp),
                title = {
                    Text(
                        text = "Day",
                        style = MaterialTheme.typography.h6
                    )
                },
                text = {
                    var items by remember {
                        mutableStateOf(
                            listOf(
                                "Monday",
                                "Tuesday",
                                "Wednesday",
                                "Thursday",
                                "Friday",
                                "Saturday",
                                "Sunday"
                            ).map {
                                ListOfDaysItems(
                                    title = it,
                                    isSelected = (it.uppercase() == selectDay) //todo
                                )
                            }
                        )
                    }
                    LazyColumn {
                        items(items.size) { i ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp)
                                    .clickable {
                                        items = items.mapIndexed { j, item ->
                                            if (i == j) item.copy(isSelected = true)
                                            else item.copy(isSelected = false)
                                        }
                                        selectDay = items[i].title.uppercase()
                                    }
                            ) {
                                if (items[i].isSelected) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(color = PurpleBackground),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = items[i].title,
                                            color = PurpleText,
                                            style = MaterialTheme.typography.subtitle1,
                                        )
                                    }
                                } else {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = items[i].title,
                                            style = MaterialTheme.typography.subtitle1,
                                        )
                                    }
                                }
                            }
                        }
                    }
                },
                buttons = {
                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(Color.White),
                            onClick = { openDialog.value = false }
                        ) {
                            Text(text = "Cancel", color = Color.Blue)
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(Color.White),
                            onClick = {
                                day.value = dayOfWeek
                                openDialog.value = false
                            }
                        ) {
                            Text(text = "Restore", color = Color.Blue)
                        }
                        Button(
                            onClick = {
                                day.value = selectDay
                                openDialog.value = false
                            }
                        ) {
                            Text(text = "Ok")
                        }
                    }
                }
            )
        }
    }
}
