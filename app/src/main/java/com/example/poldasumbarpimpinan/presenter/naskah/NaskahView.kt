package com.example.poldasumbarpimpinan.presenter.naskah

import com.example.poldasumbarpimpinan.model.ResultItemNaskah

interface NaskahView{

    fun onSuccess(message:String,result: List<ResultItemNaskah?>? = null)
    fun onError(message: String)
    fun hideProgress()
    fun hideLoading()
    fun dataKosong()
}