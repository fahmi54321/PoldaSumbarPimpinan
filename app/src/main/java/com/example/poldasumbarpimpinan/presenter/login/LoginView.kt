package com.example.poldasumbarpimpinan.presenter.login

interface LoginView {

    fun onSuccess()
    fun onError()
    fun empty()
    fun hideProgress()
    fun hideLoading()
}