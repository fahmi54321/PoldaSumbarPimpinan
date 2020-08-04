package com.example.poldasumbarpimpinan.presenter.naskah

import android.util.Log
import com.example.poldasumbarpimpinan.model.ResponseNaskah
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaskahPresenter(var naskahView: NaskahView) {

    fun getNaskah()
    {
        ConfigNetwork.getRetrofit().getNaskah().enqueue(object : Callback<ResponseNaskah>{
            override fun onFailure(call: Call<ResponseNaskah>, t: Throwable) {
                naskahView.onError(t.localizedMessage)
                naskahView.hideLoading()
                naskahView.hideProgress()
            }

            override fun onResponse(call: Call<ResponseNaskah>, response: Response<ResponseNaskah>) {
                if(response.isSuccessful)
                {
                    val data =response.body()?.result
                    if (data?.size?:0>0)
                    {
                        Log.d("Koneksi Berhasil",response.message())
                        naskahView.onSuccess(response.message(),data)
                        naskahView.hideLoading()
                        naskahView.hideProgress()
                    }
                    else
                    {
                        naskahView.dataKosong()
                        naskahView.hideLoading()
                        naskahView.hideProgress()
                    }
                }
                else
                {
                    naskahView.onError(response.message())
                    naskahView.hideLoading()
                    naskahView.hideProgress()
                }
            }

        })
    }
}