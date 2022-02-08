package com.example.tavolga.screen.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tavolga.model.Nomination
import com.example.tavolga.navigation.Screen
import com.example.tavolga.screen.mainScreen.view.ButtonBarData
import com.example.tavolga.ui.theme.background
import com.example.tavolga.ui.theme.white
import com.example.tavolga.utils.Converters

@Composable
fun NominationScreen(
    navController: NavController,
    eventName: String,
    nominationString: String
) {
    val nomination = Converters().decodeFromString<List<Nomination>>(nominationString)
    val idBar = remember { mutableStateOf("Home") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                backgroundColor = background,
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.Main.route){
                        popUpTo(Screen.Main.route){
                            inclusive = true
                        }
                    } }) {
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
            when(idBar.value){
                "Profile"->{}
                "Home"-> {
                    Column {
                        Text(
                            text = eventName,
                            modifier = Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold
                        )
                        LazyColumn(content = {
                            items(nomination){
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(10.dp)
                                    .clickable {
                                        navController.navigate(Screen.Rating.base(
                                            nomination = Converters().encodeToString(it),
                                            eventName = eventName
                                        ))
                                    }
                                    .background(white)
                                ){
                                    Column(Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = it.name)
                                    }
                                }
                            }
                        })
                    }
                }
                "Event"->{}
            }
        }
    )
}