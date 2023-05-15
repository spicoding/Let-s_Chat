package com.example.finalmorning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignUp : AppCompatActivity() {
    private lateinit var btnsignup : Button
    private lateinit var edtEmail: EditText
    private lateinit var edtUsername : EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnlogin : Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().reference
        btnlogin = findViewById(R.id.btn_login)
        btnsignup = findViewById(R.id.btn_signup)
        edtUsername = findViewById(R.id.edt_username)
        edtPassword = findViewById(R.id.edt_password)
        edtEmail = findViewById(R.id.edt_email1)



        btnlogin.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
        btnsignup.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val username = edtUsername.text.toString()

            signup(email,username,password)
        }
    }

    private fun signup(email: String, username: String, password: String){
        // logic of creating a user

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for jumping to Home activity
                    addUserToDatabase(username,email,mAuth.currentUser?.uid!!)
                    val intent = Intent(this, HomeActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Some error has occurred", Toast.LENGTH_SHORT).show()

                }
            }
    }
    private fun addUserToDatabase(username: String, email: String, uid: String){
        mDbRef = FirebaseDatabase.getInstance().reference

        mDbRef.child("user").child(uid).setValue(User(username,email,uid))


    }

}