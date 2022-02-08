package com.example.tavolga.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val name:String,
    val description:String,
    val nominations: List<Nomination>
)