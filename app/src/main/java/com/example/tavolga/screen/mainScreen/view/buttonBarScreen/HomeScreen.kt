package com.example.tavolga.screen.mainScreen.view.buttonBarScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.tavolga.R
import com.example.tavolga.api.ViewModelApi
import com.example.tavolga.model.Event
import com.example.tavolga.navigation.Screen
import com.example.tavolga.utils.Converters

@Composable
fun HomeScreen(
    navController: NavController,
    viewModelApi: ViewModelApi,
    owner: LifecycleOwner
) {
    val eventList = remember { mutableStateOf(listOf<Event>()) }

    viewModelApi.getEvent()
    viewModelApi.eventResponse.observe(owner){
        eventList.value = it
    }

    Column {
        Text(
            text = "Мероприятия",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp)
        )
        LazyColumn(content = {
            items(eventList.value){ event ->
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = AbsoluteRoundedCornerShape(10.dp)
                ) {
                    Column {
                        Text(
                            text = event.name,
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = event.description,
                            modifier = Modifier.padding(10.dp)
                        )
                        TextButton(onClick = {
                            navController.navigate(Screen.EventOpen.base(
                                Converters().encodeToString(event)
                            ))
                        }) {
                            Row {
                                Text(
                                    text = "Нажмите, чтобы перейти",
                                    modifier = Modifier.padding(10.dp)
                                )

                                Image(
                                    painter = painterResource(id = R.drawable.groupv),
                                    contentDescription = null,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }
                    }
                }
            }
        })
    }
}