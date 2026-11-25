package com.example.app_empleate

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class EmpresaRecursosHumanosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_empresa_recursos_humanos)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMenuRRHH = findViewById<ImageView>(R.id.btnMenuRRHH)
        val btnAgendarJuan = findViewById<MaterialButton>(R.id.btnAgendarJuan)
        val btnAgendarMaria = findViewById<MaterialButton>(R.id.btnAgendarMaria)

        btnMenuRRHH.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        btnAgendarJuan.setOnClickListener {
            Toast.makeText(this, "Citación agendada con Juan Pérez", Toast.LENGTH_SHORT).show()
        }

        btnAgendarMaria.setOnClickListener {
            Toast.makeText(this, "Citación agendada con María Pérez", Toast.LENGTH_SHORT).show()
        }
    }
}
