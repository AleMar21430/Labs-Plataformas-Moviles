package com.marti21430.lab10.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marti21430.lab10.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()

    }

}