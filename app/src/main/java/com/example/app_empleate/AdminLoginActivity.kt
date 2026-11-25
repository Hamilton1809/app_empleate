package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class AdminLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMenuAdminLogin = findViewById<ImageView?>(R.id.btnMenuAdminLogin)
        val etCorreoAdmin = findViewById<EditText>(R.id.etCorreoAdmin)
        val etPasswordAdmin = findViewById<EditText>(R.id.etPasswordAdmin)
        val btnIniciarSesionAdmin = findViewById<MaterialButton>(R.id.btnIniciarSesionAdmin)
        val btnGoogleAdmin = findViewById<ImageButton?>(R.id.btnGoogleAdmin)

        btnMenuAdminLogin?.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        btnIniciarSesionAdmin.setOnClickListener {
            val correo = etCorreoAdmin.text.toString().trim()
            val pass = etPasswordAdmin.text.toString().trim()

            if (correo.isEmpty()) {
                etCorreoAdmin.error = "Ingresa el correo de administrador"
                etCorreoAdmin.requestFocus()
                return@setOnClickListener
            }
            if (pass.isEmpty()) {
                etPasswordAdmin.error = "Ingresa la contraseña"
                etPasswordAdmin.requestFocus()
                return@setOnClickListener
            }

            Toast.makeText(this, "¡Bienvenido al Panel de Administración!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AdminGestionEmpresaActivity::class.java))
            finish()
        }

        btnGoogleAdmin?.setOnClickListener {
            Toast.makeText(this, "Iniciando sesión con Google Admin...", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AdminGestionEmpresaActivity::class.java))
            finish()
        }
    }
}
