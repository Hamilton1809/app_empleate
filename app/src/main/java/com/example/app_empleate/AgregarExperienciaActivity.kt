package com.example.app_empleate // Verifica tu paquete

import android.os.Bundle
import android.widget.ImageView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AgregarExperienciaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_experiencia)

        val btnRegresar = findViewById<ImageView>(R.id.btnRegresarExp)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarExp)

        // Al presionar la flechita de regresar, "matamos" esta pantalla y volvemos al Paso 2
        btnRegresar.setOnClickListener {
            finish()
        }

        // Al presionar guardar, también regresamos (por ahora)
        btnGuardar.setOnClickListener {
            finish()
        }
    }
}