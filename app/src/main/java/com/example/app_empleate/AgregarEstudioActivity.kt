package com.example.app_empleate // Verifica tu paquete

import android.os.Bundle
import android.widget.ImageView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AgregarEstudioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_estudio)

        val btnRegresar = findViewById<ImageView>(R.id.btnRegresarEstudio)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarEstudio)

        // Regresar a la pantalla anterior
        btnRegresar.setOnClickListener {
            finish()
        }

        btnGuardar.setOnClickListener {
            finish()
        }
    }
}