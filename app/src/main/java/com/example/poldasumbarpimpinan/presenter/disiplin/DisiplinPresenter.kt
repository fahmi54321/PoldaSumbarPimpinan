package com.example.poldasumbarpimpinan.presenter.disiplin

import android.util.Log
import com.example.poldasumbarpimpinan.model.ResponseDisiplin
import com.example.poldasumbarpimpinan.model.ResponseKke
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisiplinPresenter(var disiplinView: DisiplinView) {

    fun getDisiplin()
    {
        ConfigNetwork.getRetrofit().getDisiplin().enqueue(object : Callback<ResponseDisiplin>{
            override fun onFailure(call: Call<ResponseDisiplin>, t: Throwable) {
                Log.d("response gagal total",t.localizedMessage)
                disiplinView.onError(t.localizedMessage)
                disiplinView.hideLoading()
                disiplinView.hideProgress()
            }

            override fun onResponse(call: Call<ResponseDisiplin>, response: Response<ResponseDisiplin>) {
                if(response.isSuccessful)
                {
                    val data =response.body()?.result
                    if (data?.size?:0>0)
                    {
                        Log.d("response berhasil",response.message())
                        disiplinView.onSuccess(response.message(),data)
                        disiplinView.hideLoading()
                        disiplinView.hideProgress()
                    }
                    else
                    {
                        Log.d("response data kosong",response.message())
                        disiplinView.dataKosong()
                        disiplinView.hideLoading()
                        disiplinView.hideProgress()
                    }
                }
                else
                {
                    Log.d("response gagal",response.message())
                    disiplinView.onError(response.message())
                    disiplinView.hideLoading()
                    disiplinView.hideProgress()
                }
            }

        })
    }
}