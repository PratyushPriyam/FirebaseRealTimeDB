package com.assignment.firebaserealtimedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.google.firebase.database.DatabaseReference

class SplashScreen : AppCompatActivity() {
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        imageView = findViewById(R.id.imageView)
        imageView.startAnimation(AnimationUtils.loadAnimation(applicationContext, R.anim.blink))
        Handler().postDelayed(
            {
                startActivity(Intent(this, LogIn::class.java))
            }, 3000
        )
    }
}