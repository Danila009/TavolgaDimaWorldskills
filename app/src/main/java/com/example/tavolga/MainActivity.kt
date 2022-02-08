package com.example.tavolga

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.tavolga.navigation.navGraph.host.Host
import com.example.tavolga.ui.theme.TavolgaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TavolgaTheme {
                Host(
                    navHostController = rememberNavController(),
                    owner = this
                )
            }
        }
    }
}