package com.example.poldasumbarpimpinan.presenter.psh

import com.example.poldasumbarpimpinan.model.ResultItemPsh

interface PshView{

    fun onSuccess(message:String,result: List<ResultItemPsh?>? = null)
    fun onError(message: String)
    fun hideProgress()
    fun hideLoading()
    fun dataKosong()
}