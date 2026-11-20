package com.example.app_empleate // Verifica tu paquete

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CrearHojaVidaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_hoja_vida)

        // Buscamos el botón de Continuar
        val btnContinuar = findViewById<Button>(R.id.btnContinuarPaso2)

        // Al hacer clic, vamos al Paso 2
        btnContinuar.setOnClickListener {
            val intent = Intent(this, CrearHojaVidaPaso2Activity::class.java)
            startActivity(intent)
        }
    }
}