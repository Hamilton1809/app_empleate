package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configuración de los insets que ya tenías
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Buscamos el botón en tu diseño por su ID
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        // 2. Le decimos qué hacer cuando le hagan clic
        btnIngresar.setOnClickListener {
            // Creamos la "intención" de navegar a LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            // Iniciamos la nueva pantalla
            startActivity(intent)
        }
    }
}