package com.skynoff.mymedicapp.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.skynoff.mymedicapp.R
import com.skynoff.mymedicapp.home.MainActivity
import com.skynoff.mymedicapp.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
                              startActivity(Intent(this,LoginActivity::class.java))
        },2000)
    }
}