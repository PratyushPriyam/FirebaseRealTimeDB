package com.assignment.firebaserealtimedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    lateinit var emailEdt: EditText
    lateinit var passEdt: EditText
    lateinit var logInBtn: Button
    lateinit var signUpBtn: Button
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        
        auth = FirebaseAuth.getInstance()

        emailEdt = findViewById(R.id.editTextText)
        passEdt = findViewById(R.id.editTextText3)
        signUpBtn = findViewById(R.id.button2)
        signUpBtn.setOnClickListener { 
            startActivity(Intent(this, SignUp::class.java))
        }
        
        logInBtn = findViewById(R.id.button)
        logInBtn.setOnClickListener { 
            auth.signInWithEmailAndPassword(emailEdt.text.toString(), passEdt.text.toString()).addOnSuccessListener {
                Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
        }
    }
}