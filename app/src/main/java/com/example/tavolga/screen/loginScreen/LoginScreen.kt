package com.example.tavolga.screen.loginScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tavolga.api.ViewModelApi
import com.example.tavolga.model.Authorization
import com.example.tavolga.ui.theme.background

@Composable
fun LoginScreen(
    navController: NavController,
    viewModelApi: ViewModelApi
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = background,
                title = {}
            )
        }, content = {
            Column(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Система оценки работ",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "через платформу WSR",
                        color = background,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Column(Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = email.value,
                        onValueChange = {email.value = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        placeholder = { Text(text = "Электронная почта")}
                    )
                    OutlinedTextField(
                        value = password.value,
                        onValueChange = {password.value = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        placeholder = { Text(text = "Пароль")}
                    )
                    Button(
                        onClick = {
                            viewModelApi.postAuthorization(
                                Authorization(
                                    email.value,
                                    password.value
                                ),navController
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = background
                    )) {
                        Text(text = "Войти")
                    }
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp))
                    Text(
                        text = "Нет аккаунта? Создать",
                        color = Color.Blue,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp))
                }
            }
        }
    )
}