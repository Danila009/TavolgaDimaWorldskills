package com.example.tavolga.model

import kotlinx.serialization.Serializable

@Serializable
data class Criterie(
    val name:String,
    val description:String,
    val minScore:Int,
    val maxScore:Int
)