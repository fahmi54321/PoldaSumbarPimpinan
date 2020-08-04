package com.example.poldasumbarpimpinan.view

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.poldasumbarpimpinan.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var left_to_right:Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)
        txtDataBidkum.startAnimation(left_to_right)
        linearPsh.startAnimation(left_to_right)
        linearKke.startAnimation(left_to_right)
        linearDisiplin.startAnimation(left_to_right)
        linearPidana.startAnimation(left_to_right)
        linearNaskah.startAnimation(left_to_right)
        linearPeraturan.startAnimation(left_to_right)

        linearPsh.setOnClickListener {
            val intent = Intent(this,GetPshActivity::class.java)
            startActivity(intent)
        }

        linearKke.setOnClickListener {
            val intent = Intent(this,GetKkeActivity::class.java)
            startActivity(intent)
        }

        linearDisiplin.setOnClickListener {
            val intent = Intent(this,GetDisiplinActivity::class.java)
            startActivity(intent)
        }

        linearPidana.setOnClickListener {
            val intent = Intent(this,GetPidanaActivity::class.java)
            startActivity(intent)
        }

        linearNaskah.setOnClickListener {
            val intent = Intent(this,GetNaskahKermaActivity::class.java)
            startActivity(intent)
        }

        linearPeraturan.setOnClickListener {
            val intent = Intent(this,GetPeraturanActivity::class.java)
            startActivity(intent)
        }
    }
}