package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminEstrategiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_estrategia)

        val btnMenu = findViewById<ImageView>(R.id.btnMenuEstrategia)

        btnMenu.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menu.add(0, 1, 0, "Cambiar Contraseña")
            popup.menu.add(0, 2, 1, "Gestión de Empresa")
            popup.menu.add(0, 3, 2, "Crear Categorías")
            popup.menu.add(0, 4, 3, "Inicio Principal")

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    1 -> startActivity(Intent(this, AdminCambiarPasswordActivity::class.java))
                    2 -> startActivity(Intent(this, AdminGestionEmpresaActivity::class.java))
                    3 -> startActivity(Intent(this, AdminCrearCategoriasActivity::class.java))
                    4 -> {
                        val intent = Intent(this, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        }
                        startActivity(intent)
                    }
                }
                true
            }
            popup.show()
        }
    }
}
