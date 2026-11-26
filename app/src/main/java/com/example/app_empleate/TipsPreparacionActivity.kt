package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class TipsPreparacionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tips_preparacion)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRegresarTips = findViewById<ImageView>(R.id.btnRegresarTips)
        val btnMenuTips = findViewById<ImageView>(R.id.btnMenuTips)
        val tvUserEmailTipsHeader = findViewById<TextView?>(R.id.tvUserEmailTipsHeader)
        val tvCargoTipsHeader = findViewById<TextView>(R.id.tvCargoTipsHeader)
        val btnCambiarCargoTips = findViewById<MaterialButton>(R.id.btnCambiarCargoTips)
        val tvTipTiempoDetalle = findViewById<TextView>(R.id.tvTipTiempoDetalle)

        val currentUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
        val emailStr = currentUser?.email ?: "usuario@gmail.com"
        tvUserEmailTipsHeader?.text = emailStr

        val btnProbarReloj = findViewById<MaterialButton>(R.id.btnProbarReloj)
        val btnVerDiagnostico = findViewById<MaterialButton>(R.id.btnVerDiagnostico)
        val btnIniciarRutinaDiaria = findViewById<MaterialButton>(R.id.btnIniciarRutinaDiaria)
        val btnVerPreguntasTrampa = findViewById<MaterialButton>(R.id.btnVerPreguntasTrampa)

        fun cargarTipsAdaptados() {
            val cargo = PerfilUsuarioRepository.getCargoPostulacion(this)
            tvCargoTipsHeader.text = "Tips adaptados para: $cargo"

            val tipTiempoText = when {
                cargo.lowercase().contains("desarrollador") || cargo.lowercase().contains("sistemas") ->
                    "Asigna máximo 3 minutos por problema de código o algoritmo. Si te bloqueas en una prueba técnica, pasa a la siguiente solución y vuelve al final."
                cargo.lowercase().contains("administrativo") || cargo.lowercase().contains("contador") ->
                    "Asigna 45 segundos a preguntas de clasificación documental y Excel. Prioriza respuestas organizadas e información precisa."
                cargo.lowercase().contains("atención") || cargo.lowercase().contains("vendedor") ->
                    "Tómate 30 segundos para evaluar el tono empático antes de responder preguntas de casos difíciles con clientes."
                else ->
                    "Asigna máximo de 60 a 90 segundos por pregunta situacional. Administra tu tiempo con calma y evita responder con prisa."
            }
            tvTipTiempoDetalle.text = tipTiempoText
        }

        cargarTipsAdaptados()

        btnRegresarTips.setOnClickListener { finish() }
        btnMenuTips.setOnClickListener { MenuNavegacionHelper.mostrarMenu(this) }

        btnCambiarCargoTips.setOnClickListener {
            SelectorCargoDialog.mostrarSelector(this) { nuevoCargo ->
                cargarTipsAdaptados()
                Toast.makeText(this, "Cargo actualizado: $nuevoCargo", Toast.LENGTH_SHORT).show()
            }
        }

        btnProbarReloj.setOnClickListener {
            val cargo = PerfilUsuarioRepository.getCargoPostulacion(this)
            AlertDialog.Builder(this)
                .setTitle("Estrategia de Tiempo para $cargo")
                .setMessage("1. Regla de los 60 segundos por pregunta.\n2. Si una pregunta te toma más de 2 minutos, márcala y continúa.\n3. Revisa al final antes de entregar.")
                .setPositiveButton("Entendido", null)
                .show()
        }

        btnVerDiagnostico.setOnClickListener {
            startActivity(Intent(this, EntrenamientosIAActivity::class.java))
        }

        btnIniciarRutinaDiaria.setOnClickListener {
            startActivity(Intent(this, EntrenamientosIAActivity::class.java))
        }

        btnVerPreguntasTrampa.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Preguntas Trampa Frecuentes")
                .setMessage("• '¿Por qué deberíamos contratarte?' -> Destaca cómo tus fortalezas solucionan los desafíos de la empresa.\n• '¿Cuáles son tus aspiraciones salariales?' -> Investiga el promedio del mercado para tu cargo antes de responder.")
                .setPositiveButton("Entendido", null)
                .show()
        }
    }
}
