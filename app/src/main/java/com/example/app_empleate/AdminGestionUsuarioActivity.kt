package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class AdminGestionUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_gestion_usuario)

        val btnAñadir = findViewById<MaterialButton>(R.id.btnAñadirUsuario)
        val btnEliminar = findViewById<MaterialButton>(R.id.btnEliminarUsuario)
        val btnMenu = findViewById<ImageView>(R.id.btnMenuGestionUsuario)

        btnMenu.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menu.add(0, 1, 0, "Crear Categorías")
            popup.menu.add(0, 2, 1, "Gestión de Empresa")
            popup.menu.add(0, 3, 2, "Estrategia de Preparación")
            popup.menu.add(0, 4, 3, "Cambiar Contraseña")

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    1 -> startActivity(Intent(this, AdminCrearCategoriasActivity::class.java))
                    2 -> startActivity(Intent(this, AdminGestionEmpresaActivity::class.java))
                    3 -> startActivity(Intent(this, AdminEstrategiaActivity::class.java))
                    4 -> startActivity(Intent(this, AdminCambiarPasswordActivity::class.java))
                }
                true
            }
            popup.show()
        }

        btnAñadir.setOnClickListener {
            Toast.makeText(this, "Usuario añadido exitosamente", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AdminGestionEmpresaActivity::class.java))
        }

        btnEliminar.setOnClickListener {
            Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AdminGestionEmpresaActivity::class.java))
        }
    }
}
