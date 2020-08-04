package com.example.poldasumbarpimpinan.presenter.pidana

import android.util.Log
import com.example.poldasumbarpimpinan.model.ResponsePidana
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PidanaPresenter(var pidanaView: PidanaView) {

    fun getPidana()
    {
        ConfigNetwork.getRetrofit().getPidana().enqueue(object : Callback<ResponsePidana>{
            override fun onFailure(call: Call<ResponsePidana>, t: Throwable) {
                Log.d("response gagal total",t.localizedMessage)
                pidanaView.onError(t.localizedMessage)
                pidanaView.hideLoading()
                pidanaView.hideProgress()
            }

            override fun onResponse(call: Call<ResponsePidana>, response: Response<ResponsePidana>) {
                if(response.isSuccessful)
                {
                    val data =response.body()?.result
                    if (data?.size?:0>0)
                    {
                        Log.d("response berhasil",response.message())
                        pidanaView.onSuccess(response.message(),data)
                        pidanaView.hideLoading()
                        pidanaView.hideProgress()
                    }
                    else
                    {
                        Log.d("response data kosong",response.message())
                        pidanaView.dataKosong()
                        pidanaView.hideLoading()
                        pidanaView.hideProgress()
                    }
                }
                else
                {
                    Log.d("response gagal",response.message())
                    pidanaView.onError(response.message())
                    pidanaView.hideLoading()
                    pidanaView.hideProgress()
                }
            }

        })
    }
}