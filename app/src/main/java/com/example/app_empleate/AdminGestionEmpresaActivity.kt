package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class AdminGestionEmpresaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_gestion_empresa)

        val cardFinanzas = findViewById<MaterialCardView>(R.id.cardFinanzas)
        val cardRecursosHumanos = findViewById<MaterialCardView>(R.id.cardRecursosHumanos)
        val cardControlStock = findViewById<MaterialCardView>(R.id.cardControlStock)
        val btnMenu = findViewById<ImageView>(R.id.btnMenuGestionEmpresa)

        btnMenu.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menu.add(0, 1, 0, "Estrategia de Preparación")
            popup.menu.add(0, 2, 1, "Cambiar Contraseña")
            popup.menu.add(0, 3, 2, "Gestión de Usuario")
            popup.menu.add(0, 4, 3, "Crear Categorías")

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    1 -> startActivity(Intent(this, AdminEstrategiaActivity::class.java))
                    2 -> startActivity(Intent(this, AdminCambiarPasswordActivity::class.java))
                    3 -> startActivity(Intent(this, AdminGestionUsuarioActivity::class.java))
                    4 -> startActivity(Intent(this, AdminCrearCategoriasActivity::class.java))
                }
                true
            }
            popup.show()
        }

        cardFinanzas.setOnClickListener {
            Toast.makeText(this, "Módulo de Finanzas Empresa", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AdminEstrategiaActivity::class.java))
        }

        cardRecursosHumanos.setOnClickListener {
            Toast.makeText(this, "Módulo de Recursos Humanos", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AdminEstrategiaActivity::class.java))
        }

        cardControlStock.setOnClickListener {
            Toast.makeText(this, "Módulo de Control de Stock", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AdminEstrategiaActivity::class.java))
        }
    }
}
