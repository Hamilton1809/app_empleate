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

        val etCorreo = findViewById<android.widget.EditText?>(R.id.etCorreo)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)

        // Al iniciar sesión, va a Crear Hoja de Vida o EmpresaHomeActivity si el correo contiene "empresa"
        btnIniciarSesion.setOnClickListener {
            val correo = etCorreo?.text?.toString()?.lowercase() ?: ""
            if (correo.contains("empresa")) {
                startActivity(Intent(this, EmpresaHomeActivity::class.java))
            } else {
                startActivity(Intent(this, CrearHojaVidaActivity::class.java))
            }
        }

        btnIniciarSesion.setOnLongClickListener {
            startActivity(Intent(this, EmpresaHomeActivity::class.java))
            true
        }
    }
}