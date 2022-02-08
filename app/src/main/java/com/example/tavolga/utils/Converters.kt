package com.example.tavolga.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    inline fun <reified T> decodeFromString (string:String):T{
        return Json.decodeFromString(string)
    }

    inline fun <reified T> encodeToString(base:T):String{
        return Json.encodeToString(base)
    }
}