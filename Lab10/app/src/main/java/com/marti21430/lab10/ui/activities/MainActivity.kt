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

        val prefs = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE)
        setContentView(R.layout.activity_main)
        actionBar?.hide()

        if (!prefs.contains("Log")) {
            prefs.edit().putBoolean("Log", false)
            prefs.edit().apply()
            prefs.edit().commit()
        }
    }
}