package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class EntrenamientosIAActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenamientos_ia)

        val btnMenuEntrenamientos = findViewById<ImageView>(R.id.btnMenuEntrenamientos)

        val btnIniciarEntrenamientoIA = findViewById<MaterialButton>(R.id.btnIniciarEntrenamientoIA)
        val btnIniciarSimulacro = findViewById<MaterialButton>(R.id.btnIniciarSimulacro)
        val btnIniciarTemas = findViewById<MaterialButton>(R.id.btnIniciarTemas)
        val btnIniciarExamen = findViewById<MaterialButton>(R.id.btnIniciarExamen)

        val cardTipTiempo = findViewById<MaterialCardView>(R.id.cardTipTiempo)
        val cardTipDebiles = findViewById<MaterialCardView>(R.id.cardTipDebiles)
        val cardTipDiario = findViewById<MaterialCardView>(R.id.cardTipDiario)

        val btnComenzarAvance = findViewById<MaterialButton>(R.id.btnComenzarAvance)

        // Menú desplegable superior (☰)
        btnMenuEntrenamientos.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menu.add(0, 1, 0, "Imprimir hoja de vida")
            popup.menu.add(0, 2, 1, "Editar hoja de vida")
            popup.menu.add(0, 3, 2, "Inicio")

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    1 -> startActivity(Intent(this, ImprimirHojaVidaActivity::class.java))
                    2 -> startActivity(Intent(this, CrearHojaVidaActivity::class.java))
                    3 -> startActivity(Intent(this, MainActivity::class.java))
                }
                true
            }
            popup.show()
        }

        btnIniciarEntrenamientoIA.setOnClickListener {
            Toast.makeText(this, "Iniciando Entrenamiento Inteligente con IA...", Toast.LENGTH_SHORT).show()
        }

        btnIniciarSimulacro.setOnClickListener {
            Toast.makeText(this, "Iniciando Simulacro Rápido (5 preguntas)...", Toast.LENGTH_SHORT).show()
        }

        btnIniciarTemas.setOnClickListener {
            Toast.makeText(this, "Cargando categorías por temas...", Toast.LENGTH_SHORT).show()
        }

        btnIniciarExamen.setOnClickListener {
            Toast.makeText(this, "Preparando Modo Examen Real con temporizador...", Toast.LENGTH_SHORT).show()
        }

        cardTipTiempo.setOnClickListener {
            mostrarTip(
                "Controla tu tiempo",
                "En pruebas psicotécnicas y entrevistas técnicas, asigna un máximo de 60 a 90 segundos por pregunta. Si te bloqueas, avanza y vuelve al final."
            )
        }

        cardTipDebiles.setOnClickListener {
            mostrarTip(
                "Identifica tus temas débiles",
                "Revisa tu gráfico de avance semanal para detectar las áreas con menor porcentaje y dedica sesiones cortas de refuerzo antes de postularte."
            )
        }

        cardTipDiario.setOnClickListener {
            mostrarTip(
                "Entrena 10 min diarios",
                "La práctica constante de 10 minutos al día mejora la retención y reduce los nervios frente a pruebas reales de selección."
            )
        }

        btnComenzarAvance.setOnClickListener {
            Toast.makeText(this, "Comenzando sesión recomendada según tu avance...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarTip(titulo: String, descripcion: String) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(descripcion)
            .setPositiveButton("Entendido", null)
            .show()
    }
}