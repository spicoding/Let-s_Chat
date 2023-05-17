package com.example.finalmorning

// IMPORTING SERVICES AND PACKAGES

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

//Create a class for the Login Activity

class LogIn : AppCompatActivity() {
    //Initializing variables

    private lateinit var btnsignup : Button
    private lateinit var edt_email:EditText
    private lateinit var edt_password:EditText
    private lateinit var btnlogin : Button
    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        supportActionBar?.hide()

        //Initializing Firebase Authentication
        mAuth = FirebaseAuth.getInstance()

        btnsignup = findViewById(R.id.btn_signup)
        btnlogin = findViewById(R.id.btn_login)
        edt_email = findViewById(R.id.edt_email)
        edt_password = findViewById(R.id.edt_password)

        // Setting Up Button Navigation

        btnsignup.setOnClickListener {

            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        // Creating the login Procedure

        btnlogin.setOnClickListener {
            val email = edt_email.text.toString()
            val password = edt_password.text.toString()

            login(email,password)
        }
    }

    // Creating the login functionality

    private fun login(email: String, password: String) {
        //logic of logging in user with reference to Firebase

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code for logging in user
                    val intent = Intent(this, HomeActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                }
            }

    }
}