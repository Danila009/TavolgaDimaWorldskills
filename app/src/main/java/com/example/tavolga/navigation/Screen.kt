package com.example.tavolga.navigation

const val EVENT_ARGUMENT = "event"
const val NOMINATION_ARGUMENT ="nomination"
const val EVENT_NAME_ARGUMENT = "event_name"
const val USERNAME_ARGUMENT = "username"
const val CRITERIE_ARGUMENT = "criterie"

const val ROUTE = "route"
const val LOGIN_ROUTE = "login_route"
const val MAIN_ROUTE = "main_route"

sealed class Screen(val route:String){
    object Login:Screen("login_screen")
    object Main:Screen("main_screen")
    object EventOpen:Screen("event_open_screen?event={event}"){
        fun base(string: String):String = "event_open_screen?event=$string"
    }
    object Nomination:Screen("nominations_screen?nomination={nomination}&event_name={event_name}"){
        fun base(
            nomination: String,
            eventName:String
        ):String = "nominations_screen?nomination=$nomination&event_name=$eventName"
    }
    object Rating:Screen("rating_screen?nomination={nomination}&event_name={event_name}"){
        fun base(
            nomination: String,
            eventName:String
        ):String = "rating_screen?nomination=$nomination&event_name=$eventName"
    }
    object Criterie:Screen("criterie_screen?username={username}&criterie={criterie}"){
        fun base(
            username:String,
            criterie:String
        ):String = "criterie_screen?username=$username&criterie=$criterie"
    }
}
