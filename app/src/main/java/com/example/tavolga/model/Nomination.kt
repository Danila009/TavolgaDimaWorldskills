package com.example.tavolga.model

import kotlinx.serialization.Serializable

@Serializable
data class Nomination(
    val name:String,
    val description:String,
    val criteries:List<Criterie>
)
