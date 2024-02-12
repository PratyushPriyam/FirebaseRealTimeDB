package com.assignment.firebaserealtimedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var edt1: EditText
    lateinit var edt2: EditText
    lateinit var regNoEdt: EditText
    lateinit var createBtn: Button
    lateinit var readBtn: Button
    lateinit var delBtn: Button
    lateinit var firebaseRef: DatabaseReference
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt1 = findViewById(R.id.editTextText1)
        edt2 = findViewById(R.id.editTextText2)
        regNoEdt = findViewById(R.id.regNoEdt)

        firebaseRef = FirebaseDatabase.getInstance().reference

        createBtn = findViewById(R.id.createBtn)
        createBtn.setOnClickListener{
            firebaseRef.child("Users").child(regNoEdt.text.toString()).setValue(User(edt1.text.toString(), edt2.text.toString().toInt()))
                .addOnSuccessListener {
                    Toast.makeText(this, "User Successfully Added", Toast.LENGTH_SHORT).show()
                    edt1.setText("")
                    edt2.setText("")
                    regNoEdt.setText("")
                }.addOnFailureListener {
                    Toast.makeText(this, "Oops, something went wrong", Toast.LENGTH_SHORT).show()
                }
        }

        readBtn = findViewById(R.id.readBtn)
        readBtn.setOnClickListener {
            firebaseRef.child("Users").child(regNoEdt.text.toString()).get().addOnSuccessListener {
                val valueMap = it.value as? Map<*, *>
                if(valueMap != null) {
                    edt1.setText(valueMap["name"].toString())
                    edt2.setText(valueMap["age"].toString())
                }
                Toast.makeText(this, it.value.toString(), Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Oops, something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        delBtn = findViewById(R.id.delBtn)
        delBtn.setOnClickListener {
            firebaseRef.child("Users").child(regNoEdt.text.toString()).removeValue().addOnSuccessListener {
                Toast.makeText(this, "Data deleted", Toast.LENGTH_SHORT).show()
                edt1.setText("")
                edt2.setText("")
                regNoEdt.setText("")
            }.addOnFailureListener {
                Toast.makeText(this, "Oops, something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}