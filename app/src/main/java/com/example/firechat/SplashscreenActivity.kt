package com.example.firechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplashscreenActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        var auth = FirebaseAuth.getInstance()
        var currentUser = auth.currentUser

        if (currentUser?.email.isNullOrEmpty()){
            startActivity(Intent(baseContext,LoginActivity::class.java))
        }
        else{
            startActivity(Intent(baseContext,FireChatActivity::class.java))
        }

    }
}
