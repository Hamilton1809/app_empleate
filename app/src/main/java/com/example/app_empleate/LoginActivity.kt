package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val intentRegistro = {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        findViewById<TextView?>(R.id.tvIrRegistro)?.setOnClickListener { intentRegistro() }
        findViewById<TextView?>(R.id.tvRegistrarse)?.setOnClickListener { intentRegistro() }

        // Al iniciar sesión, va a Crear Hoja de Vida (Paso 1)
        findViewById<Button>(R.id.btnIniciarSesion).setOnClickListener {
            startActivity(Intent(this, CrearHojaVidaActivity::class.java))
        }
    }
}