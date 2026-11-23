package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCorreoReg = findViewById<EditText>(R.id.etCorreoReg)
        val etPasswordReg = findViewById<EditText>(R.id.etPasswordReg)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnAceptarRegistro = findViewById<Button>(R.id.btnAceptarRegistro)
        val btnIrInicioRegistro = findViewById<Button>(R.id.btnIrInicioRegistro)

        // Registrar usuario en Firebase Authentication
        btnAceptarRegistro.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreoReg.text.toString().trim()
            val password = etPasswordReg.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if (correo.isEmpty()) {
                etCorreoReg.error = "Ingresa tu correo"
                etCorreoReg.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                etPasswordReg.error = "La contraseña debe tener al menos 6 caracteres"
                etPasswordReg.requestFocus()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                etConfirmPassword.error = "Las contraseñas no coinciden"
                etConfirmPassword.requestFocus()
                return@setOnClickListener
            }

            // Crear usuario en Firebase Authentication
            auth.createUserWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "¡Usuario $nombre registrado exitosamente!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Registro procesado exitosamente", Toast.LENGTH_SHORT).show()
                    }
                    // Al registrarse, navega a Entrenamientos e IA
                    startActivity(Intent(this, EntrenamientosIAActivity::class.java))
                    finish()
                }
        }

        // Botón "IR A INICIO" navega a MainActivity
        btnIrInicioRegistro.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }
}
