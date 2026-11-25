package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class EmpresaLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_empresa_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMenuEmpresaLogin = findViewById<ImageView?>(R.id.btnMenuEmpresaLogin)
        val etIdEmpresa = findViewById<EditText>(R.id.etIdEmpresa)
        val etPasswordEmpresa = findViewById<EditText>(R.id.etPasswordEmpresa)
        val btnIniciarSesionEmpresa = findViewById<MaterialButton>(R.id.btnIniciarSesionEmpresa)
        val tvRegistrarPerfilEmpresa = findViewById<TextView?>(R.id.tvRegistrarPerfilEmpresa)
        val btnGoogleEmpresa = findViewById<ImageButton?>(R.id.btnGoogleEmpresa)

        btnMenuEmpresaLogin?.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        btnIniciarSesionEmpresa.setOnClickListener {
            val idEmp = etIdEmpresa.text.toString().trim()
            val pass = etPasswordEmpresa.text.toString().trim()

            if (idEmp.isEmpty()) {
                etIdEmpresa.error = "Ingresa tu correo o ID de empresa"
                etIdEmpresa.requestFocus()
                return@setOnClickListener
            }
            if (pass.isEmpty()) {
                etPasswordEmpresa.error = "Ingresa la contraseña"
                etPasswordEmpresa.requestFocus()
                return@setOnClickListener
            }

            Toast.makeText(this, "¡Bienvenido al Portal de Empresa!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, EmpresaHomeActivity::class.java))
            finish()
        }

        tvRegistrarPerfilEmpresa?.setOnClickListener {
            startActivity(Intent(this, EmpresaRegistroActivity::class.java))
        }

        btnGoogleEmpresa?.setOnClickListener {
            Toast.makeText(this, "Iniciando sesión con Google Empresa...", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, EmpresaHomeActivity::class.java))
            finish()
        }
    }
}
