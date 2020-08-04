package com.example.poldasumbarpimpinan.network

import com.example.poldasumbarpimpinan.model.*
import retrofit2.Call
import retrofit2.http.*

interface ConfigInterface {

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("username") username:String,
        @Field("password") password:String
    ): Call<ResponseLogin>

    @GET("getDataPshGlobal.php")
    fun getPsh(): Call<ResponsePsh>

    @GET("getDataKkeGlobal.php")
    fun getKke(): Call<ResponseKke>

    @GET("getDataDisiplinGlobal.php")
    fun getDisiplin(): Call<ResponseDisiplin>

    @GET("getDataPidanaGlobal.php")
    fun getPidana(): Call<ResponsePidana>

    @GET("getDataNaskahGlobal.php")
    fun getNaskah(): Call<ResponseNaskah>

    @GET("getDataPeraturanGlobal.php")
    fun getPeraturan(): Call<ResponsePeraturan>

    @GET("searchingDataPshGlobal.php")
    fun searchPsh(
        @Query("kesatuan") kesatuan:String,
        @Query("nama") nama:String
    ): Call<ResponsePsh>

    @GET("searchingDataDisiplinGlobal.php")
    fun searchDisiplin(
        @Query("kesatuan") kesatuan:String,
        @Query("nama_pelaku") nama_pelaku:String
    ): Call<ResponseDisiplin>

    @GET("searchingDataKkeGlobal.php")
    fun searchKke(
        @Query("kesatuan") kesatuan:String,
        @Query("nama_pelaku") nama_pelaku:String
    ): Call<ResponseKke>

    @GET("searchingDataPidanaGlobal.php")
    fun searchPidana(
        @Query("kesatuan") kesatuan:String,
        @Query("nama_pelaku") nama_pelaku:String
    ): Call<ResponsePidana>

    @GET("searchingDataNaskahGlobal.php")
    fun searchNaskah(
        @Query("kesatuan") kesatuan:String,
        @Query("tentang") tentang:String
    ): Call<ResponseNaskah>

    @GET("searchingDataPeraturanGlobal.php")
    fun searchPeraturan(
        @Query("pilih_peraturan") pilih_peraturan:String,
        @Query("nama_pejabat") nama_pejabat:String
    ): Call<ResponsePeraturan>
}