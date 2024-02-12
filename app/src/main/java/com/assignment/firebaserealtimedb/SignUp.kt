package com.assignment.firebaserealtimedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var emailEdt: EditText
    lateinit var passEdt: EditText
    lateinit var passConfEdt: EditText
    lateinit var signUpBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        emailEdt = findViewById(R.id.editTextText4)
        passEdt = findViewById(R.id.editTextText5)
        passConfEdt = findViewById(R.id.editTextText6)
        signUpBtn = findViewById(R.id.button3)

        signUpBtn.setOnClickListener {
            if(emailEdt.text == null || passEdt.text == null || passConfEdt.text == null) {
                Toast.makeText(this, "Some fields are empty", Toast.LENGTH_SHORT).show()
            }
            else if(passEdt.text.toString() != passConfEdt.text.toString()) {
                Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.createUserWithEmailAndPassword(emailEdt.text.toString(), passEdt.text.toString()).addOnSuccessListener {
                    Toast.makeText(this, "New user with email: ${emailEdt.text.toString()} created", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LogIn::class.java))
                }.addOnFailureListener {
                    Toast.makeText(this, "${it}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}