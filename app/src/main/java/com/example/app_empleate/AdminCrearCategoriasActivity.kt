package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class AdminCrearCategoriasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_crear_categorias)

        val spConocimientoPuesto = findViewById<Spinner>(R.id.spConocimientoPuesto)
        val spPreguntasEntrevistas = findViewById<Spinner>(R.id.spPreguntasEntrevistas)
        val spHabilidadesComunicacion = findViewById<Spinner>(R.id.spHabilidadesComunicacion)
        val spSimulacrosPractica = findViewById<Spinner>(R.id.spSimulacrosPractica)
        val spSeguimientoPostEntrevista = findViewById<Spinner>(R.id.spSeguimientoPostEntrevista)

        val btnSiguiente = findViewById<MaterialButton>(R.id.btnSiguienteCategorias)
        val btnMenu = findViewById<ImageView>(R.id.btnMenuCrearCategorias)

        // Configurar opciones de ejemplo para cada Spinner
        val opcionesConocimiento = arrayOf("CONOCIMIENTO DEL PUESTO", "Nivel Básico", "Nivel Intermedio", "Nivel Avanzado")
        val opcionesPreguntas = arrayOf("PREGUNTAS DE ENTREVISTAS", "Técnicas", "Psicotécnicas", "Situacionales")
        val opcionesHabilidades = arrayOf("HABILIDADES DE COMUNICACION", "Asertividad", "Trabajo en Equipo", "Liderazgo")
        val opcionesSimulacros = arrayOf("SIMULACROS Y PRACTICA", "Simulacro Rápido", "Simulacro Intensivo")
        val opcionesSeguimiento = arrayOf("SEGUIMIENTO POST-ENTREVISTA", "Feedback Inicial", "Evaluación Final")

        val createAdapter = { items: Array<String> ->
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        }

        spConocimientoPuesto.adapter = createAdapter(opcionesConocimiento)
        spPreguntasEntrevistas.adapter = createAdapter(opcionesPreguntas)
        spHabilidadesComunicacion.adapter = createAdapter(opcionesHabilidades)
        spSimulacrosPractica.adapter = createAdapter(opcionesSimulacros)
        spSeguimientoPostEntrevista.adapter = createAdapter(opcionesSeguimiento)

        btnMenu.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menu.add(0, 1, 0, "Gestión de Usuario")
            popup.menu.add(0, 2, 1, "Gestión de Empresa")
            popup.menu.add(0, 3, 2, "Estrategia de Preparación")
            popup.menu.add(0, 4, 3, "Cambiar Contraseña")
            popup.menu.add(0, 5, 4, "Inicio Principal")

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    1 -> startActivity(Intent(this, AdminGestionUsuarioActivity::class.java))
                    2 -> startActivity(Intent(this, AdminGestionEmpresaActivity::class.java))
                    3 -> startActivity(Intent(this, AdminEstrategiaActivity::class.java))
                    4 -> startActivity(Intent(this, AdminCambiarPasswordActivity::class.java))
                    5 -> {
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

        btnSiguiente.setOnClickListener {
            Toast.makeText(this, "Categorías guardadas. Avanzando...", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AdminGestionUsuarioActivity::class.java))
        }
    }
}
