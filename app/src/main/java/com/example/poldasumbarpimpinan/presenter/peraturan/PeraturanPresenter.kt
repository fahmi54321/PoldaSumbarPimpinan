package com.example.poldasumbarpimpinan.presenter.peraturan

import android.util.Log
import com.example.poldasumbarpimpinan.model.ResponsePeraturan
import com.example.poldasumbarpimpinan.model.ResponsePsh
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeraturanPresenter(var peraturanView: PeraturanView) {

    fun getPeraturan()
    {
        ConfigNetwork.getRetrofit().getPeraturan().enqueue(object : Callback<ResponsePeraturan>{
            override fun onFailure(call: Call<ResponsePeraturan>, t: Throwable) {
                peraturanView.onError(t.localizedMessage)
                peraturanView.hideLoading()
                peraturanView.hideProgress()
            }

            override fun onResponse(call: Call<ResponsePeraturan>, response: Response<ResponsePeraturan>) {
                if(response.isSuccessful)
                {
                    val data =response.body()?.result
                    if (data?.size?:0>0)
                    {
                        Log.d("Koneksi Berhasil",response.message())
                        peraturanView.onSuccess(response.message(),data)
                        peraturanView.hideLoading()
                        peraturanView.hideProgress()
                    }
                    else
                    {
                        peraturanView.dataKosong()
                        peraturanView.hideLoading()
                        peraturanView.hideProgress()
                    }
                }
                else
                {
                    peraturanView.onError(response.message())
                    peraturanView.hideLoading()
                    peraturanView.hideProgress()
                }
            }

        })
    }
}