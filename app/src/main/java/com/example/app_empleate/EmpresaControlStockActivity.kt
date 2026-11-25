package com.example.app_empleate

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class EmpresaControlStockActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_empresa_control_stock)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMenuStock = findViewById<ImageView>(R.id.btnMenuStock)
        val btnRegistrarStock = findViewById<MaterialButton>(R.id.btnRegistrarStock)

        btnMenuStock.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        btnRegistrarStock.setOnClickListener {
            Toast.makeText(this, "Registro de insumo agregado al stock", Toast.LENGTH_SHORT).show()
        }
    }
}
