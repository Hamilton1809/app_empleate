package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class EmpresaRegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa_registro)

        val btnEnviar = findViewById<MaterialButton>(R.id.btnEnviarRegistroEmpresa)
        val cbTerminos = findViewById<CheckBox>(R.id.cbTerminosEmpresa)

        btnEnviar.setOnClickListener {
            if (!cbTerminos.isChecked) {
                Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "¡Perfil de Empresa Registrado con Éxito!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, EmpresaHomeActivity::class.java))
            finish()
        }
    }
}
