package com.example.poldasumbarpimpinan.presenter.psh

import android.util.Log
import com.example.poldasumbarpimpinan.model.ResponsePsh
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PshPresenter(var pshView: PshView) {

    fun getPsh()
    {
        ConfigNetwork.getRetrofit().getPsh().enqueue(object : Callback<ResponsePsh>{
            override fun onFailure(call: Call<ResponsePsh>, t: Throwable) {
                pshView.onError(t.localizedMessage)
                pshView.hideLoading()
                pshView.hideProgress()
            }

            override fun onResponse(call: Call<ResponsePsh>, response: Response<ResponsePsh>) {
                if(response.isSuccessful)
                {
                    val data =response.body()?.result
                    if (data?.size?:0>0)
                    {
                        Log.d("Koneksi Berhasil",response.message())
                        pshView.onSuccess(response.message(),data)
                        pshView.hideLoading()
                        pshView.hideProgress()
                    }
                    else
                    {
                        pshView.dataKosong()
                        pshView.hideLoading()
                        pshView.hideProgress()
                    }
                }
                else
                {
                    pshView.onError(response.message())
                    pshView.hideLoading()
                    pshView.hideProgress()
                }
            }

        })
    }
}