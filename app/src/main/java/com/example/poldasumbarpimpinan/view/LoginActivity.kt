package com.example.poldasumbarpimpinan.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.poldasumbarpimpinan.R
import com.example.poldasumbarpimpinan.presenter.login.LoginPresenter
import com.example.poldasumbarpimpinan.presenter.login.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),
    LoginView {
    var presenter: LoginPresenter?=null
    lateinit var top_to_bottom : Animation
    lateinit var bottom_to_top : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        top_to_bottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom)
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)

        logo.startAnimation(top_to_bottom)
        editTextPassword.startAnimation(bottom_to_top)
        editTextUsername.startAnimation(bottom_to_top)
        buttonLogin.startAnimation(bottom_to_top)

        presenter = LoginPresenter(this)

        buttonLogin.setOnClickListener {
            buttonLogin.setBackgroundResource(R.drawable.bttn_simpan_loading)
            buttonLogin.setText("Loading...")
            buttonLogin.isEnabled = false
            progressBar.visibility = View.VISIBLE
            var username = editTextUsername.text.toString()
            var password = editTextPassword.text.toString()

            presenter?.login(username, password)
        }
    }

    override fun onSuccess() {
        toast("Berhasil Login")
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onError() {
        toast("Gagal Login")
    }

    override fun empty() {
        toast("Username dan Password tidak boleh kosong")
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun hideLoading() {
        buttonLogin.setBackgroundResource(R.drawable.bttn_simpan)
        buttonLogin.setText("Login")
        buttonLogin.isEnabled = true
    }

    fun toast(message:String)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

}