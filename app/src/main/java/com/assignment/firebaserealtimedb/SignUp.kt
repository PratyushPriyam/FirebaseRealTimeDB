package com.assignment.firebaserealtimedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.sign

class SignUp : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var emailEdt: EditText
    lateinit var passEdt: EditText
    lateinit var confPassEdt: EditText
    lateinit var signupBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        emailEdt = findViewById(R.id.editTextText4)
        passEdt = findViewById(R.id.editTextText5)
        confPassEdt = findViewById(R.id.editTextText6)
        signupBtn = findViewById(R.id.button3)
        
        signupBtn.setOnClickListener { 
            auth.createUserWithEmailAndPassword(emailEdt.text.toString(), passEdt.text.toString()).addOnSuccessListener {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LogIn::class.java))
            }.addOnFailureListener {
                Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
            }
        }

    }
}