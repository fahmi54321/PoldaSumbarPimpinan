package com.example.poldasumbarpimpinan.presenter.kke

import com.example.poldasumbarpimpinan.model.ResultItemKke
interface KkeView{

    fun onSuccess(message:String,result: List<ResultItemKke?>? = null)
    fun onError(message: String)
    fun hideProgress()
    fun hideLoading()
    fun dataKosong()
}