package com.example.poldasumbarpimpinan.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ConfigNetwork {
    companion object
    {
        fun getRetrofit() : ConfigInterface
        {
            val builder = OkHttpClient.Builder()
            builder.readTimeout(20, TimeUnit.SECONDS)
            builder.connectTimeout(20, TimeUnit.SECONDS)
            builder.writeTimeout(20, TimeUnit.SECONDS)

            val client = builder.build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://e-infohukum.poldasumbar.com/1/2/3/api/AplikasiBidkumAdmin/API/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(ConfigInterface::class.java)

            return service
        }
    }
}