package com.example.finalmorning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


// START OF CODE FOR THE LANDING PAGE
class MainActivity2 : AppCompatActivity() {

    // Initializing tjhe button
    private lateinit var buttonmainactivity : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main2)

        buttonmainactivity = findViewById(R.id.btn_main_screen)

        // Functionality for button to open the Main Activity

        buttonmainactivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}