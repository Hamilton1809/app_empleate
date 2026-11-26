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
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.lifecycleScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

class EmpresaLoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var credentialManager: CredentialManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_empresa_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        credentialManager = CredentialManager.create(this)

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

        // Inicio de sesión real con Google
        btnGoogleEmpresa?.setOnClickListener {
            iniciarSesionGoogleEmpresa()
        }
    }

    private fun iniciarSesionGoogleEmpresa() {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(getString(R.string.default_web_client_id))
            .setAutoSelectEnabled(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                val result: GetCredentialResponse = credentialManager.getCredential(
                    request = request,
                    context = this@EmpresaLoginActivity
                )
                manejarCredencialGoogle(result)
            } catch (e: GetCredentialException) {
                Toast.makeText(
                    this@EmpresaLoginActivity,
                    "No se pudo iniciar con Google: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
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
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "¡Bienvenido al Portal de Empresa!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, EmpresaHomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Error al autenticar en Firebase: ${task.exception?.localizedMessage}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}
