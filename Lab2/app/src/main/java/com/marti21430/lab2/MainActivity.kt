package com.marti21430.lab2

import android.widget.Button
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toast_mesg = findViewById(R.id.button2) as Button
        toast_mesg.setOnClickListener {
            Toast.makeText(this@MainActivity, "Alejandro Jose Martinez de Leon 21430", Toast.LENGTH_SHORT).show()
        }
    }
}
