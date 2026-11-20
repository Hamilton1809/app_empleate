package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Al registrarse, va a Crear Hoja de Vida (Paso 1)
        findViewById<Button>(R.id.btnAceptarRegistro).setOnClickListener {
            startActivity(Intent(this, CrearHojaVidaActivity::class.java))
        }
    }
}