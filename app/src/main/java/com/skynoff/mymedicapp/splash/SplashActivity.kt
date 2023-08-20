package com.skynoff.mymedicapp.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.skynoff.mymedicapp.OnboardingActivity
import com.skynoff.mymedicapp.R
import com.skynoff.mymedicapp.home.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
                              startActivity(Intent(this,OnboardingActivity::class.java))
            finish()
        },2000)
    }
}