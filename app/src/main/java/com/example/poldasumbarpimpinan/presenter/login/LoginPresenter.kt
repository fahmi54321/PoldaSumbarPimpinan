package com.example.poldasumbarpimpinan.presenter.login

import com.example.poldasumbarpimpinan.model.ResponseLogin
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(var loginView: LoginView) {

    fun login(username:String,password:String)
    {
        if (username.isNotEmpty() && password.isNotEmpty())
        {
            ConfigNetwork.getRetrofit().login(username,password).enqueue(object : Callback<ResponseLogin>{
                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    loginView.onError()
                    loginView.hideLoading()
                    loginView.hideProgress()
                }

                override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                    if (response.isSuccessful)
                    {
                        if (response.body()?.message.equals("berhasil"))
                        {
                            loginView.onSuccess()
                            loginView.hideLoading()
                            loginView.hideProgress()
                        }
                        else
                        {
                            loginView.onError()
                            loginView.hideLoading()
                            loginView.hideProgress()
                        }
                    }
                    else
                    {
                        loginView.onError()
                        loginView.hideLoading()
                        loginView.hideProgress()
                    }
                }

            })
        }
        else
        {
            loginView.empty()
            loginView.hideLoading()
            loginView.hideProgress()
        }
    }
}