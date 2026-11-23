package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth

class EntrenamientosIAActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenamientos_ia)

        DatosHojaVidaRepository.cargarDePrefs(this)

        val btnMenuEntrenamientos = findViewById<ImageView>(R.id.btnMenuEntrenamientos)
        val tvUserEmailHeader = findViewById<TextView?>(R.id.tvUserEmailHeader)
        val tvCargoActualIA = findViewById<TextView?>(R.id.tvCargoActualIA)
        val btnCambiarCargoIA = findViewById<MaterialButton?>(R.id.btnCambiarCargoIA)

        // Mostrar el correo del usuario autenticado en el encabezado
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userEmail = currentUser?.email
        if (!userEmail.isNullOrEmpty()) {
            tvUserEmailHeader?.text = userEmail
        }

        // Actualizar el cargo objetivo guardado
        fun actualizarCargoUi() {
            val cargo = PerfilUsuarioRepository.getCargoPostulacion(this)
            tvCargoActualIA?.text = "Cargo objetivo actual: $cargo"
        }
        actualizarCargoUi()

        // Cambiar en qué se va a postular
        btnCambiarCargoIA?.setOnClickListener {
            val cargosPopulares = arrayOf(
                "Auxiliar Administrativo",
                "Desarrollador Junior",
                "Atención al Cliente",
                "Vendedor / Comercial",
                "Contador / Finanzas",
                "Diseñador Gráfico",
                "Ingeniero de Sistemas",
                "Escribir otro cargo..."
            )

            AlertDialog.Builder(this)
                .setTitle("¿En qué cargo te vas a postular?")
                .setItems(cargosPopulares) { _, which ->
                    if (which == cargosPopulares.size - 1) {
                        // Escribir otro cargo personalizado
                        val input = EditText(this)
                        input.hint = "Ej: Analista de datos"
                        AlertDialog.Builder(this)
                            .setTitle("Ingresa tu cargo objetivo")
                            .setView(input)
                            .setPositiveButton("Guardar") { _, _ ->
                                val nuevoCargo = input.text.toString().trim()
                                if (nuevoCargo.isNotEmpty()) {
                                    PerfilUsuarioRepository.setCargoPostulacion(this, nuevoCargo)
                                    actualizarCargoUi()
                                    Toast.makeText(this, "Cargo objetivo guardado en tu cuenta", Toast.LENGTH_SHORT).show()
                                }
                            }
                            .setNegativeButton("Cancelar", null)
                            .show()
                    } else {
                        val nuevoCargo = cargosPopulares[which]
                        PerfilUsuarioRepository.setCargoPostulacion(this, nuevoCargo)
                        actualizarCargoUi()
                        Toast.makeText(this, "Cargo objetivo actualizado: $nuevoCargo", Toast.LENGTH_SHORT).show()
                    }
                }
                .show()
        }

        val btnIniciarEntrenamientoIA = findViewById<MaterialButton?>(R.id.btnIniciarEntrenamientoIA)
        val btnIniciarSimulacro = findViewById<MaterialButton?>(R.id.btnIniciarSimulacro)
        val btnIniciarTemas = findViewById<MaterialButton?>(R.id.btnIniciarTemas)
        val btnIniciarExamen = findViewById<MaterialButton?>(R.id.btnIniciarExamen)

        val btnIrPostularOfertas = findViewById<MaterialButton?>(R.id.btnIrPostularOfertas)
        val btnIrBuscarEmpleo = findViewById<MaterialButton?>(R.id.btnIrBuscarEmpleo)
        val btnIrCrearHojaVidaIA = findViewById<MaterialButton?>(R.id.btnIrCrearHojaVidaIA)

        val cardTipTiempo = findViewById<MaterialCardView?>(R.id.cardTipTiempo)
        val cardTipDebiles = findViewById<MaterialCardView?>(R.id.cardTipDebiles)
        val cardTipDiario = findViewById<MaterialCardView?>(R.id.cardTipDiario)

        val btnComenzarAvance = findViewById<MaterialButton?>(R.id.btnComenzarAvance)

        // Menú desplegable superior (☰) con panel moderno
        btnMenuEntrenamientos?.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        // Botón destacado para Crear / Editar Hoja de Vida
        btnIrCrearHojaVidaIA?.setOnClickListener {
            startActivity(Intent(this, CrearHojaVidaActivity::class.java))
        }

        // Botones de navegación
        btnIrPostularOfertas?.setOnClickListener {
            startActivity(Intent(this, PostularOfertasActivity::class.java))
        }

        btnIrBuscarEmpleo?.setOnClickListener {
            startActivity(Intent(this, BuscarEmpleoActivity::class.java))
        }

        // Botones de entrenamiento
        btnIniciarEntrenamientoIA?.setOnClickListener {
            Toast.makeText(this, "Iniciando Entrenamiento Inteligente IA...", Toast.LENGTH_SHORT).show()
        }

        btnIniciarSimulacro?.setOnClickListener {
            Toast.makeText(this, "Iniciando Simulacro Rápido...", Toast.LENGTH_SHORT).show()
        }

        btnIniciarTemas?.setOnClickListener {
            Toast.makeText(this, "Cargando categorías por temas...", Toast.LENGTH_SHORT).show()
        }

        btnIniciarExamen?.setOnClickListener {
            Toast.makeText(this, "Preparando Modo Examen Real...", Toast.LENGTH_SHORT).show()
        }

        // Tip 1: Control de Tiempo adaptado al cargo del usuario
        cardTipTiempo?.setOnClickListener {
            val cargo = PerfilUsuarioRepository.getCargoPostulacion(this)
            val tipAdaptado = when {
                cargo.lowercase().contains("desarrollador") || cargo.lowercase().contains("sistemas") ->
                    "Para postularte a $cargo: Asigna máximo 3 minutos por lógica de algoritmos. Si te trabas en una prueba técnica, pasa a la siguiente solución parcial."
                cargo.lowercase().contains("administrativo") || cargo.lowercase().contains("contador") ->
                    "Para postularte a $cargo: Dedica 45 segundos a preguntas de clasificación documental y Excel rápido. Prioriza respuestas precisas."
                cargo.lowercase().contains("atención") || cargo.lowercase().contains("vendedor") ->
                    "Para postularte a $cargo: En pruebas situacionales de atención al cliente, tómate 30 segundos para evaluar el tono empático antes de responder."
                else ->
                    "Para postularte a $cargo: Asigna máximo de 60 a 90 segundos por pregunta situacional. Mantén la calma y administra tu tiempo."
            }
            mostrarTip("Controla tu tiempo para $cargo", tipAdaptado)
        }

        // Tip 2: Identifica temas débiles
        cardTipDebiles?.setOnClickListener {
            mostrarTip(
                "Identifica tus temas débiles",
                "Revisa tu gráfico de avance semanal para detectar las áreas con menor porcentaje y dedica sesiones cortas de refuerzo antes de postularte."
            )
        }

        // Tip 3 / Quiz: Entrena 10 min diarios adaptado al cargo
        cardTipDiario?.setOnClickListener {
            iniciarQuiz10Minutos()
        }

        btnComenzarAvance?.setOnClickListener {
            iniciarQuiz10Minutos()
        }
    }

    private fun iniciarQuiz10Minutos() {
        val cargo = PerfilUsuarioRepository.getCargoPostulacion(this)
        val preguntas = when {
            cargo.lowercase().contains("desarrollador") || cargo.lowercase().contains("sistemas") -> arrayOf(
                "1. ¿Cuál es la diferencia entre un proceso y un hilo?",
                "2. ¿Cómo manejas el control de versiones en equipo con Git?",
                "3. ¿Qué estrategia usas al encontrar un error en producción?"
            )
            cargo.lowercase().contains("administrativo") || cargo.lowercase().contains("contador") -> arrayOf(
                "1. ¿Cómo priorizas tareas cuando hay múltiples entregas simultáneas?",
                "2. ¿Qué fórmulas avanzadas de Excel manejas con soltura?",
                "3. ¿Cómo aseguras la confidencialidad de la información?"
            )
            cargo.lowercase().contains("atención") || cargo.lowercase().contains("vendedor") -> arrayOf(
                "1. ¿Cómo manejas a un cliente insatisfecho o exigente?",
                "2. ¿Cuál es tu técnica principal para cerrar una venta difícil?",
                "3. ¿Cómo mantienes una comunicación clara bajo presión?"
            )
            else -> arrayOf(
                "1. ¿Cuáles son tus mayores fortalezas en el trabajo?",
                "2. Describe una situación desafiante que resolviste con éxito.",
                "3. ¿Cómo te organizas para cumplir tus objetivos diarios?"
            )
        }

        AlertDialog.Builder(this)
            .setTitle("Práctica Inteligente (10 min) para $cargo")
            .setItems(preguntas) { _, which ->
                Toast.makeText(this, "Excelente respuesta registrada para pregunta ${which + 1}", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Completar sesión", null)
            .show()
    }

    private fun mostrarTip(titulo: String, descripcion: String) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(descripcion)
            .setPositiveButton("Entendido", null)
            .show()
    }
}
