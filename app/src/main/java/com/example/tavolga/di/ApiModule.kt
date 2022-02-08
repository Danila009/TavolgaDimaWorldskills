package com.example.tavolga.di

import android.content.Context
import android.content.SharedPreferences
import com.example.tavolga.api.RepositoryApi
import com.example.tavolga.api.TavolgaApi
import com.example.tavolga.utils.Constants.BASE_URL
import com.example.tavolga.utils.Constants.PREFS_TOKEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providerApi(
        tavolgaApi: TavolgaApi
    ) = RepositoryApi(tavolgaApi)

    @Provides
    @Singleton
    fun providerRetrofit(
        okHttpClient: OkHttpClient
    ):TavolgaApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TavolgaApi::class.java)

    @Provides
    @Singleton
    fun providerOkhttp(
        sharedPreferences: SharedPreferences
    ):OkHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .header("Authorization","Bearer " +
                        "${sharedPreferences.getString(PREFS_TOKEN,"")}")
                .build()
            it.proceed(request)
        }
        .build()

    @Provides
    @Singleton
    fun providerShared(
        @ApplicationContext context: Context
    ):SharedPreferences = context.getSharedPreferences(
        PREFS_TOKEN, Context.MODE_PRIVATE
    )
}