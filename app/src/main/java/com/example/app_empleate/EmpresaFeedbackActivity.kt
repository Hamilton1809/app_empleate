package com.example.app_empleate

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class EmpresaFeedbackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa_feedback)

        findViewById<ImageView>(R.id.btnRegresarFeedback).setOnClickListener {
            finish()
        }

        findViewById<ImageView?>(R.id.btnMenuEmpresaFeedback)?.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        findViewById<MaterialButton>(R.id.btnSeleccionarArchivo).setOnClickListener {
            Toast.makeText(this, "Seleccionando archivo de imagen...", Toast.LENGTH_SHORT).show()
        }

        findViewById<MaterialButton>(R.id.btnEnviarFeedback).setOnClickListener {
            Toast.makeText(this, "¡Gracias por enviar tu feedback!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
