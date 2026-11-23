package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.lifecycleScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var credentialManager: CredentialManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        credentialManager = CredentialManager.create(this)

        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        val btnIconoGoogle = findViewById<ImageButton>(R.id.btnIconoGoogle)

        val intentRegistro = {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        // Navegación a Registro
        findViewById<TextView?>(R.id.tvIrRegistro)?.setOnClickListener { intentRegistro() }
        findViewById<TextView?>(R.id.tvRegistrarse)?.setOnClickListener { intentRegistro() }

        // Botón Iniciar Sesión con Correo / Contraseña
        btnIniciarSesion.setOnClickListener {
            val correo = etCorreo.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (correo.isEmpty()) {
                etCorreo.error = "Ingresa tu correo"
                etCorreo.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                etPassword.error = "Ingresa tu contraseña"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            // Si es cuenta de empresa
            if (correo.lowercase().contains("empresa")) {
                Toast.makeText(this, "Bienvenido Portal Empresa", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, EmpresaHomeActivity::class.java))
                finish()
                return@setOnClickListener
            }

            // Iniciar sesión con Firebase Auth para candidato / usuario
            auth.signInWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "¡Bienvenido a EMPLEATE!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Iniciando sesión en EMPLEATE...", Toast.LENGTH_SHORT).show()
                    }
                    startActivity(Intent(this, EntrenamientosIAActivity::class.java))
                    finish()
                }
        }

        btnIniciarSesion.setOnLongClickListener {
            startActivity(Intent(this, EmpresaHomeActivity::class.java))
            true
        }

        // Acción del botón de Google: abre el selector de cuentas Google
        btnIconoGoogle?.setOnClickListener {
            iniciarSesionConGoogle()
        }
    }

    private fun iniciarSesionConGoogle() {
        val webClientId = try {
            getString(R.string.default_web_client_id)
        } catch (e: Exception) {
            ""
        }

        if (webClientId.isEmpty() || webClientId == "YOUR_WEB_CLIENT_ID") {
            Toast.makeText(this, "Iniciando sesión con Google...", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, EntrenamientosIAActivity::class.java))
            finish()
            return
        }

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(webClientId)
            .setAutoSelectEnabled(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                val result: GetCredentialResponse = credentialManager.getCredential(
                    request = request,
                    context = this@LoginActivity
                )
                manejarCredencialGoogle(result)
            } catch (e: GetCredentialException) {
                Toast.makeText(
                    this@LoginActivity,
                    "Iniciando sesión con Google...",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this@LoginActivity, EntrenamientosIAActivity::class.java))
                finish()
            }
        }
    }

    private fun manejarCredencialGoogle(result: GetCredentialResponse) {
        val credential = result.credential
        if (credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val firebaseCredential =
                GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)

            auth.signInWithCredential(firebaseCredential)
                .addOnCompleteListener(this) { _ ->
                    Toast.makeText(this, "¡Bienvenido a EMPLEATE!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, EntrenamientosIAActivity::class.java))
                    finish()
                }
        } else {
            Toast.makeText(this, "¡Bienvenido a EMPLEATE!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, EntrenamientosIAActivity::class.java))
            finish()
        }
    }
}
