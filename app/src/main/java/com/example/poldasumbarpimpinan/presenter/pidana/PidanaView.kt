package com.example.poldasumbarpimpinan.presenter.pidana
import com.example.poldasumbarpimpinan.model.ResultItemPidana

interface PidanaView{

    fun onSuccess(message:String,result: List<ResultItemPidana?>? = null)
    fun onError(message: String)
    fun hideProgress()
    fun hideLoading()
    fun dataKosong()
}