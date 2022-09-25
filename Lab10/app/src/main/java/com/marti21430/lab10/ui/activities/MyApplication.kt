package com.marti21430.lab10.ui.activities

import android.app.Application

class MyApplication : Application() {
    companion object {
        var username: String = ""
        //"jcdurini@uvg.edu.gt"
    }
    override fun onCreate() {
        super.onCreate()
    }
}