package com.something.subfirstjetpack.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.something.subfirstjetpack.databinding.ActivitySplashBinding
import com.something.subfirstjetpack.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var handler: Handler
    private val TIME_MILLIS_SECOND = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handler = Handler(Looper.getMainLooper()).apply{

            postDelayed({
                val moveHomeActivity = Intent(this@SplashActivity, HomeActivity::class.java)
                startActivity(moveHomeActivity)
                finish()
            }, TIME_MILLIS_SECOND.toLong())
        }
    }
}