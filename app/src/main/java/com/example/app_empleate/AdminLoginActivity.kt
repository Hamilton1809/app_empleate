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

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var credentialManager: CredentialManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        credentialManager = CredentialManager.create(this)

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

        // Inicio de sesión real con Google
        btnGoogleAdmin?.setOnClickListener {
            iniciarSesionGoogleAdmin()
        }
    }

    private fun iniciarSesionGoogleAdmin() {
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
                    context = this@AdminLoginActivity
                )
                manejarCredencialGoogle(result)
            } catch (e: GetCredentialException) {
                Toast.makeText(
                    this@AdminLoginActivity,
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
                        Toast.makeText(this, "¡Bienvenido al Panel de Administración!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, AdminGestionEmpresaActivity::class.java))
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
