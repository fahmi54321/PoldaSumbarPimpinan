package com.example.poldasumbarpimpinan.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.poldasumbarpimpinan.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val thread = Thread(Runnable {
            try {
                Thread.sleep(5000)
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            } finally {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }
        })
        thread.start()
    }
}