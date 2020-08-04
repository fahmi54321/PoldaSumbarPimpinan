package com.example.poldasumbarpimpinan.presenter.peraturan

import com.example.poldasumbarpimpinan.model.ResultItemPeraturan
interface PeraturanView{

    fun onSuccess(message:String,result: List<ResultItemPeraturan?>? = null)
    fun onError(message: String)
    fun hideProgress()
    fun hideLoading()
    fun dataKosong()
}