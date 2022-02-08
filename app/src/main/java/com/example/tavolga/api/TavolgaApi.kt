package com.example.tavolga.api

import com.example.tavolga.model.Authorization
import com.example.tavolga.model.Event
import com.example.tavolga.model.Header
import com.example.tavolga.utils.Constants.GET_EVENT
import com.example.tavolga.utils.Constants.POST_AUTHORIZATION
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TavolgaApi {

    @POST(POST_AUTHORIZATION)
    suspend fun postAuthorization(
        @Body authorization: Authorization
    ):Response<Header>

    @GET(GET_EVENT)
    suspend fun getEvent():Response<List<Event>>
}