package com.example.tavolga.api

import com.example.tavolga.model.Authorization
import com.example.tavolga.model.Event
import com.example.tavolga.model.Header
import retrofit2.Response
import javax.inject.Inject

class RepositoryApi @Inject constructor(
    private val tavolgaApi: TavolgaApi
) {
    suspend fun postAuthorization(
        authorization: Authorization
    ):Response<Header> = tavolgaApi.postAuthorization(
        authorization
    )

    suspend fun getEvent():Response<List<Event>> = tavolgaApi.getEvent()
}