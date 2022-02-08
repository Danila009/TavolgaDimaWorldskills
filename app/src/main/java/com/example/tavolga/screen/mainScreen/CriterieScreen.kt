package com.example.tavolga.screen.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
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
import com.example.tavolga.model.Criterie
import com.example.tavolga.navigation.Screen
import com.example.tavolga.screen.mainScreen.view.ButtonBarData
import com.example.tavolga.ui.theme.background
import com.example.tavolga.ui.theme.white
import com.example.tavolga.utils.Converters

@Composable
fun CriterieScreen(
    navController:NavController,
    criterieString: String,
    userName:String
) {
    val idBar = remember { mutableStateOf("Home") }
    val criterie = Converters().decodeFromString<List<Criterie>>(criterieString)

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
                            text = userName,
                            modifier = Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold
                        )

                        LazyColumn(content = {
                            item{
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(10.dp)
                                    .background(background)
                                ){
                                    Column(Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Row {
                                            Icon(
                                                imageVector = Icons.Default.Person,
                                                contentDescription = null,
                                                modifier = Modifier.padding(start = 10.dp)
                                            )
                                            Text(text = "Профиль участника",
                                                modifier = Modifier.padding(start = 20.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            items(criterie){
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(10.dp)
                                    .clickable {

                                    }
                                    .background(white)
                                ){
                                    Row {
                                        Row(Modifier.fillMaxSize(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start) {
                                            Text(text = it.name)

                                            Row(Modifier.fillMaxSize(),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.End) {
                                                repeat(5){
                                                    Card(
                                                        modifier = Modifier
                                                            .padding(5.dp)
                                                            .background(white),
                                                        elevation = 5.dp,
                                                        shape = AbsoluteRoundedCornerShape(5.dp)
                                                    ){
                                                        Text(text = when (it) {
                                                            0 -> "1"
                                                            1 -> "2"
                                                            2 -> "3"
                                                            3 -> "4"
                                                            4 -> "5"
                                                            else -> it.toString()
                                                        })
                                                    }
                                                }
                                            }
                                        }
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