package com.example.finalmorning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    private lateinit var btnsignup : Button
    private lateinit var edt_email:EditText
    private lateinit var edt_password:EditText
    private lateinit var btnlogin : Button
    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        btnsignup = findViewById(R.id.btn_signup)
        btnlogin = findViewById(R.id.btn_login)
        edt_email = findViewById(R.id.edt_email)
        edt_password = findViewById(R.id.edt_password)


        btnsignup.setOnClickListener {

            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            val email = edt_email.text.toString()
            val password = edt_password.text.toString()

            login(email,password)
        }
    }

    private fun login(email: String, password: String) {
        //logic of logging in user

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