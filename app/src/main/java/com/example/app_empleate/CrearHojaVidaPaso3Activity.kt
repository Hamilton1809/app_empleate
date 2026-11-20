package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CrearHojaVidaPaso3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_hoja_vida_paso3)

        val btnFinalizarHV = findViewById<Button>(R.id.btnFinalizarHV)
        val btnAgregarCurso = findViewById<Button>(R.id.btnAgregarCurso)

        btnAgregarCurso.setOnClickListener {
            val intent = Intent(this, AgregarCursoActivity::class.java)
            startActivity(intent)
        }

        btnFinalizarHV.setOnClickListener {
            Toast.makeText(this, "¡Hoja de Vida Creada Exitosamente!", Toast.LENGTH_LONG).show()

            // Navegamos a la pantalla de opciones de impresión/descarga
            val intent = Intent(this, ImprimirHojaVidaActivity::class.java)
            startActivity(intent)

            finish() // Cerramos el Paso 3
        }
    }
}