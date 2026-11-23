package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CrearHojaVidaPaso2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_hoja_vida_paso2)

        findViewById<ImageView?>(R.id.btnMenuPaso2)?.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        val etHvCargo = findViewById<EditText>(R.id.etHvCargo)
        val etHvAnosExp = findViewById<EditText>(R.id.etHvAnosExp)
        val etHvAreasExp = findViewById<EditText>(R.id.etHvAreasExp)
        val etHvFortalezas = findViewById<EditText>(R.id.etHvFortalezas)
        val etHvCaracteristica = findViewById<EditText>(R.id.etHvCaracteristica)

        val rgExperiencia = findViewById<RadioGroup>(R.id.rgExperiencia)
        val btnAgregarExperiencia = findViewById<Button>(R.id.btnAgregarExperiencia)
        val etInstitucion = findViewById<EditText>(R.id.etInstitucion)
        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val btnAgregarEstudio = findViewById<Button>(R.id.btnAgregarEstudio)
        val btnContinuarPaso3 = findViewById<Button>(R.id.btnContinuarPaso3)

        // Cargar datos previos
        if (DatosHojaVidaRepository.cargoDeseado.isNotEmpty()) etHvCargo.setText(DatosHojaVidaRepository.cargoDeseado)
        if (DatosHojaVidaRepository.anosExperiencia.isNotEmpty()) etHvAnosExp.setText(DatosHojaVidaRepository.anosExperiencia)
        if (DatosHojaVidaRepository.areasExperiencia.isNotEmpty()) etHvAreasExp.setText(DatosHojaVidaRepository.areasExperiencia)
        if (DatosHojaVidaRepository.fortalezas.isNotEmpty()) etHvFortalezas.setText(DatosHojaVidaRepository.fortalezas)
        if (DatosHojaVidaRepository.caracteristicas.isNotEmpty()) etHvCaracteristica.setText(DatosHojaVidaRepository.caracteristicas)

        btnAgregarExperiencia.isEnabled = false
        btnAgregarExperiencia.alpha = 0.5f

        rgExperiencia.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbSiExperiencia) {
                btnAgregarExperiencia.isEnabled = true
                btnAgregarExperiencia.alpha = 1.0f
            } else if (checkedId == R.id.rbNoExperiencia) {
                btnAgregarExperiencia.isEnabled = false
                btnAgregarExperiencia.alpha = 0.5f
            }
        }

        btnAgregarExperiencia.setOnClickListener {
            startActivity(Intent(this, AgregarExperienciaActivity::class.java))
        }

        btnAgregarEstudio.setOnClickListener {
            startActivity(Intent(this, AgregarEstudioActivity::class.java))
        }

        btnContinuarPaso3.setOnClickListener {
            val cargo = etHvCargo.text.toString().trim()
            val anosExp = etHvAnosExp.text.toString().trim()
            val areasExp = etHvAreasExp.text.toString().trim()
            val fortalezas = etHvFortalezas.text.toString().trim()

            if (cargo.isEmpty()) {
                etHvCargo.error = "¿A qué cargo deseas postularte? es obligatorio *"
                etHvCargo.requestFocus()
                return@setOnClickListener
            }
            if (anosExp.isEmpty()) {
                etHvAnosExp.error = "Años de experiencia es obligatorio *"
                etHvAnosExp.requestFocus()
                return@setOnClickListener
            }
            if (areasExp.isEmpty()) {
                etHvAreasExp.error = "Áreas de experiencia es obligatorio *"
                etHvAreasExp.requestFocus()
                return@setOnClickListener
            }
            if (fortalezas.isEmpty()) {
                etHvFortalezas.error = "Principales fortalezas es obligatorio *"
                etHvFortalezas.requestFocus()
                return@setOnClickListener
            }

            // Guardar datos y recordar el cargo deseado para la IA en PerfilUsuarioRepository
            DatosHojaVidaRepository.cargoDeseado = cargo
            DatosHojaVidaRepository.anosExperiencia = anosExp
            DatosHojaVidaRepository.areasExperiencia = areasExp
            DatosHojaVidaRepository.fortalezas = fortalezas
            DatosHojaVidaRepository.caracteristicas = etHvCaracteristica.text.toString().trim()
            DatosHojaVidaRepository.guardarEnPrefs(this)

            PerfilUsuarioRepository.setCargoPostulacion(this, cargo)

            val intent = Intent(this, CrearHojaVidaPaso3Activity::class.java)
            startActivity(intent)
        }
    }
}
