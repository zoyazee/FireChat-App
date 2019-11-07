package com.example.firechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.etEmail
import kotlinx.android.synthetic.main.activity_registration.etPassword

class RegistrationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        auth = FirebaseAuth.getInstance()

        tvLogin.setOnClickListener {
            startActivity(Intent(baseContext,FireChatActivity::class.java))
        }

        btnRegister.setOnClickListener {
            var email: String = etEmail.text.toString()
            var password: String = etPassword.text.toString()
            Toast.makeText(baseContext,email, Toast.LENGTH_LONG).show()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "createUserWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this@RegistrationActivity, FireChatActivity::class.java)
                        // start your next activity
                        startActivity(intent)
                    } else {
                        Log.w("TAG", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,task.exception?.message ,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


            }
        }
}
