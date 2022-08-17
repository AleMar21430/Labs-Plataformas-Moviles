package com.marti21430.lab2

import android.content.Intent
import android.net.Uri
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
        val Direcciones = findViewById(R.id.button) as Button
        Direcciones.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("geo:14.611189270542207,-90.485456403024"))
            //https://www.google.com/maps/place/Pizza+Vesuvio+%E2%80%A2+Cayal%C3%A1/@14.6096919,-90.4879125,17z/data=!4m5!3m4!1s0x8589a4b4142b1dff:0x11b3c522940c2e18!8m2!3d14.6086548!4d-90.4867692
            val Maps = Intent.createChooser(intent, "Launch Maps")
            startActivity(Maps)
        }
        val Detalles = findViewById(R.id.button4) as Button
        Detalles.setOnClickListener{
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
        val Descargar = findViewById(R.id.button5) as Button
        Descargar.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.YoStarEN.Arknights&hl=es_GT&gl=US")))
        }

    }
}
