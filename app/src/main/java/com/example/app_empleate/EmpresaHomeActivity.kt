package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class EmpresaHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa_home)

        val btnMenuEmpresa = findViewById<ImageView>(R.id.btnMenuEmpresa)

        val btnVerPostuladosAuxiliar = findViewById<MaterialButton>(R.id.btnVerPostuladosAuxiliar)
        val btnVerPostuladosDev = findViewById<MaterialButton>(R.id.btnVerPostuladosDev)
        val btnVerPostuladosCliente = findViewById<MaterialButton>(R.id.btnVerPostuladosCliente)

        val btnNavInicio = findViewById<LinearLayout>(R.id.btnNavInicio)
        val btnNavBuscar = findViewById<LinearLayout>(R.id.btnNavBuscar)
        val btnNavAgenda = findViewById<LinearLayout>(R.id.btnNavAgenda)
        val btnNavPerfil = findViewById<LinearLayout>(R.id.btnNavPerfil)

        // Menú desplegable superior (☰)
        btnMenuEmpresa.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menu.add(0, 1, 0, "Publicar nueva oferta")
            popup.menu.add(0, 2, 1, "Cambiar contraseña")
            popup.menu.add(0, 3, 2, "Feedback y Comentarios")
            popup.menu.add(0, 4, 3, "Ir a Inicio principal")

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    1 -> startActivity(Intent(this, EmpresaPostulacionOfertasActivity::class.java))
                    2 -> startActivity(Intent(this, EmpresaCambiarPasswordActivity::class.java))
                    3 -> startActivity(Intent(this, EmpresaFeedbackActivity::class.java))
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

        // Listeners para las tarjetas de vacantes
        btnVerPostuladosAuxiliar.setOnClickListener {
            Toast.makeText(this, "Viendo candidatos para Auxiliar Administrativo", Toast.LENGTH_SHORT).show()
        }

        btnVerPostuladosDev.setOnClickListener {
            Toast.makeText(this, "Viendo candidatos para Desarrollador Junior", Toast.LENGTH_SHORT).show()
        }

        btnVerPostuladosCliente.setOnClickListener {
            Toast.makeText(this, "Viendo candidatos para Atención al Cliente", Toast.LENGTH_SHORT).show()
        }

        // Listeners para la barra de navegación inferior
        btnNavInicio.setOnClickListener {
            Toast.makeText(this, "Ya estás en la pantalla principal de Empresa", Toast.LENGTH_SHORT).show()
        }

        btnNavBuscar.setOnClickListener {
            startActivity(Intent(this, EmpresaPostulacionOfertasActivity::class.java))
        }

        btnNavAgenda.setOnClickListener {
            Toast.makeText(this, "Sección de Agenda y Entrevistas", Toast.LENGTH_SHORT).show()
        }

        btnNavPerfil.setOnClickListener {
            startActivity(Intent(this, EmpresaRegistroActivity::class.java))
        }
    }
}
