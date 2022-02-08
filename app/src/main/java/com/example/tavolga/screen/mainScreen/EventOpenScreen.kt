package com.example.tavolga.screen.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tavolga.model.Event
import com.example.tavolga.navigation.Screen
import com.example.tavolga.screen.mainScreen.view.ButtonBarData
import com.example.tavolga.ui.theme.background
import com.example.tavolga.utils.Converters

@Composable
fun EventOpenScreen(
    navController: NavController,
    eventString: String
) {
    val event = Converters().decodeFromString<Event>(eventString)

    val idBar = remember { mutableStateOf("Home") }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = background,
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Main.route){
                            popUpTo(Screen.Main.route){
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            )
        }, bottomBar = {
            BottomNavigation(backgroundColor = background) {
                ButtonBarData.values().forEach { data ->
                    BottomNavigationItem(
                        selected = idBar.value == data.name,
                        onClick = { idBar.value = data.name },
                        label = {
                            Text(text = data.title)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = data.image),
                                contentDescription = null,
                                modifier = Modifier.size(25.dp)
                            )
                        }, selectedContentColor = Color.Black,
                        unselectedContentColor = Color.White
                    )
                }
            }
        }, content = {
            when (idBar.value) {
                "Profile" -> {}
                "Home" -> {
                    Column {
                        Text(
                            text = event.name,
                            modifier = Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Button(
                            onClick = { navController.navigate(Screen.Nomination.base(
                                nomination = Converters().encodeToString(event.nominations),
                                eventName = event.name
                            )) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            colors = ButtonDefaults.buttonColors(background)
                        ) {
                            Text(text = "Список номинаций")
                        }

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                        ) {
                            Text(text = "Познакомиться с критериями")
                        }

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                        ) {
                            Text(text = "Рабочая группа")
                        }

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                        ) {
                            Text(text = "Обратная связь")
                        }
                    }
                }
                "Event" -> {}
            }
        }
    )
}