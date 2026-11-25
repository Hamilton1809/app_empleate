package com.example.app_empleate

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class EmpresaFinanzasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_empresa_finanzas)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMenuFinanzas = findViewById<ImageView>(R.id.btnMenuFinanzas)
        val btnGenerarReporte = findViewById<MaterialButton>(R.id.btnGenerarReporteFinanzas)

        btnMenuFinanzas.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        btnGenerarReporte.setOnClickListener {
            Toast.makeText(this, "Reporte Financiero generado exitosamente", Toast.LENGTH_SHORT).show()
        }
    }
}
