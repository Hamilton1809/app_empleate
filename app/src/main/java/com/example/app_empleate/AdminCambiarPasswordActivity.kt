package com.example.app_empleate

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class AdminCambiarPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa_cambiar_password)

        findViewById<MaterialButton>(R.id.btnCambiarPasswordEmpresa).setOnClickListener {
            Toast.makeText(this, "Contraseña administrativa actualizada con éxito", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
