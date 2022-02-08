package com.example.tavolga.api

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.tavolga.model.Authorization
import com.example.tavolga.model.Event
import com.example.tavolga.navigation.MAIN_ROUTE
import com.example.tavolga.utils.Constants.PREFS_TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ViewModelApi @Inject constructor(
    private val repositoryApi: RepositoryApi,
    private val sharedPreferences: SharedPreferences
):ViewModel() {

    val eventResponse:MutableLiveData<List<Event>> = MutableLiveData()

    fun postAuthorization(authorization: Authorization, navController: NavController){
        viewModelScope.launch {
            try {
                val response = repositoryApi.postAuthorization(authorization)
                if (response.isSuccessful){
                    sharedPreferences.edit()
                        .putString(PREFS_TOKEN,response.body()?.access_token)
                        .apply()
                    navController.navigate(MAIN_ROUTE)
                }
            }catch (e:Exception){

            }
        }
    }

    fun getEvent(){
        viewModelScope.launch {
            try {
                eventResponse.value = repositoryApi.getEvent().body()
            }catch (e:Exception){
                Log.e("Retrofit:",e.message.toString())
            }
        }
    }
}