package com.example.poldasumbarpimpinan.presenter.disiplin

import com.example.poldasumbarpimpinan.model.ResultItemDisiplin
interface DisiplinView{

    fun onSuccess(message:String,result: List<ResultItemDisiplin?>? = null)
    fun onError(message: String)
    fun hideProgress()
    fun hideLoading()
    fun dataKosong()
}