package com.marti21430.lab10.ui.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.marti21430.lab10.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val storedData = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE)
        storedData.edit().putBoolean("Logged", false)
        storedData.edit().apply()
        if (storedData.getBoolean("Logged", true)){
            setContentView(R.layout.activity_main)
            actionBar?.hide()
        }else{
            setContentView(R.layout.activity_main)
            actionBar?.hide()
        }
    }
}