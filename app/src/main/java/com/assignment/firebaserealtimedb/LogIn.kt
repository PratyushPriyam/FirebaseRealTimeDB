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

        emailEdt = findViewById(R.id.editTextText)
        passEdt = findViewById(R.id.editTextText3)
        logInBtn = findViewById(R.id.button)
        signUpBtn = findViewById(R.id.button2)
        auth = FirebaseAuth.getInstance()

        signUpBtn.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        logInBtn.setOnClickListener {
            if (emailEdt.text == null || passEdt.text == null) {
                Toast.makeText(this, "Email or password cannot be null", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.signInWithEmailAndPassword(emailEdt.text.toString(), passEdt.text.toString()).addOnSuccessListener {
                    Toast.makeText(this, "user with email: ${emailEdt.text.toString()} logged in", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }.addOnFailureListener{
                    Toast.makeText(this, "${it}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}