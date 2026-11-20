package com.example.app_empleate // Recuerda verificar tu paquete

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AgregarCursoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_curso)

        findViewById<ImageView>(R.id.btnRegresarCurso).setOnClickListener { finish() }
        findViewById<Button>(R.id.btnGuardarCurso).setOnClickListener { finish() }
    }
}