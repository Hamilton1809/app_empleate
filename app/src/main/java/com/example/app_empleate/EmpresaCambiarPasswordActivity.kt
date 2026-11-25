package com.example.app_empleate

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class EmpresaCambiarPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa_cambiar_password)

        findViewById<ImageView>(R.id.btnRegresarPassword).setOnClickListener {
            finish()
        }

        findViewById<ImageView?>(R.id.btnMenuEmpresaPassword)?.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        findViewById<MaterialButton>(R.id.btnCambiarPasswordEmpresa).setOnClickListener {
            Toast.makeText(this, "Contraseña de empresa actualizada correctamente", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
