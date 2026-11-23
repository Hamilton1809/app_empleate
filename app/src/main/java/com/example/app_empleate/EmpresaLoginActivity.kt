package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class EmpresaLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa_login)

        val btnIniciarSesionEmpresa = findViewById<MaterialButton>(R.id.btnIniciarSesionEmpresa)
        val tvOlvidastePassword = findViewById<TextView>(R.id.tvOlvidastePassword)
        val tvRegistrarPerfilEmpresa = findViewById<TextView>(R.id.tvRegistrarPerfilEmpresa)

        val btnGoogleEmpresa = findViewById<ImageView>(R.id.btnGoogleEmpresa)
        val btnAppleEmpresa = findViewById<ImageView>(R.id.btnAppleEmpresa)
        val btnFacebookEmpresa = findViewById<ImageView>(R.id.btnFacebookEmpresa)

        val btnNavInicio = findViewById<LinearLayout>(R.id.btnNavInicioLogin)
        val btnNavBuscar = findViewById<LinearLayout>(R.id.btnNavBuscarLogin)
        val btnNavAgenda = findViewById<LinearLayout>(R.id.btnNavAgendaLogin)
        val btnNavPerfil = findViewById<LinearLayout>(R.id.btnNavPerfilLogin)

        btnIniciarSesionEmpresa.setOnClickListener {
            startActivity(Intent(this, EmpresaHomeActivity::class.java))
        }

        tvOlvidastePassword.setOnClickListener {
            startActivity(Intent(this, EmpresaCambiarPasswordActivity::class.java))
        }

        tvRegistrarPerfilEmpresa.setOnClickListener {
            startActivity(Intent(this, EmpresaRegistroActivity::class.java))
        }

        btnGoogleEmpresa.setOnClickListener {
            Toast.makeText(this, "Iniciando sesión con Google...", Toast.LENGTH_SHORT).show()
        }

        btnAppleEmpresa.setOnClickListener {
            Toast.makeText(this, "Iniciando sesión con Apple...", Toast.LENGTH_SHORT).show()
        }

        btnFacebookEmpresa.setOnClickListener {
            Toast.makeText(this, "Iniciando sesión con Facebook...", Toast.LENGTH_SHORT).show()
        }

        btnNavInicio.setOnClickListener {
            startActivity(Intent(this, EmpresaHomeActivity::class.java))
        }

        btnNavBuscar.setOnClickListener {
            startActivity(Intent(this, EmpresaPostulacionOfertasActivity::class.java))
        }

        btnNavAgenda.setOnClickListener {
            Toast.makeText(this, "Sección Agenda", Toast.LENGTH_SHORT).show()
        }

        btnNavPerfil.setOnClickListener {
            startActivity(Intent(this, EmpresaRegistroActivity::class.java))
        }
    }
}
