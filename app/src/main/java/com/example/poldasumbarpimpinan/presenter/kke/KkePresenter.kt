package com.example.poldasumbarpimpinan.presenter.kke

import android.util.Log
import com.example.poldasumbarpimpinan.model.ResponseKke
import com.example.poldasumbarpimpinan.model.ResponsePsh
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KkePresenter(var kkeView: KkeView) {

    fun getKke()
    {
        ConfigNetwork.getRetrofit().getKke().enqueue(object : Callback<ResponseKke>{
            override fun onFailure(call: Call<ResponseKke>, t: Throwable) {
                Log.d("response gagal total",t.localizedMessage)
                kkeView.onError(t.localizedMessage)
                kkeView.hideLoading()
                kkeView.hideProgress()
            }

            override fun onResponse(call: Call<ResponseKke>, response: Response<ResponseKke>) {
                if(response.isSuccessful)
                {
                    val data =response.body()?.result
                    if (data?.size?:0>0)
                    {
                        Log.d("response berhasil",response.message())
                        kkeView.onSuccess(response.message(),data)
                        kkeView.hideLoading()
                        kkeView.hideProgress()
                    }
                    else
                    {
                        Log.d("response data kosong",response.message())
                        kkeView.dataKosong()
                        kkeView.hideLoading()
                        kkeView.hideProgress()
                    }
                }
                else
                {
                    Log.d("response gagal",response.message())
                    kkeView.onError(response.message())
                    kkeView.hideLoading()
                    kkeView.hideProgress()
                }
            }

        })
    }
}