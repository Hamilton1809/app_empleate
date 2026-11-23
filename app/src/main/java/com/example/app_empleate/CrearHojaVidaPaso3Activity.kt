package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CrearHojaVidaPaso3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_hoja_vida_paso3)

        findViewById<ImageView?>(R.id.btnMenuPaso3)?.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        val etHabilidadesPersonales = findViewById<EditText>(R.id.etHabilidadesPersonales)
        val etHerramientas = findViewById<EditText>(R.id.etHerramientas)
        val etIdioma = findViewById<EditText>(R.id.etIdioma)
        val etNivelIdioma = findViewById<EditText>(R.id.etNivelIdioma)
        val etTipoLicencia = findViewById<EditText>(R.id.etTipoLicencia)
        val etReferencias = findViewById<EditText>(R.id.etReferencias)
        val etLogros = findViewById<EditText>(R.id.etLogros)

        val btnAgregarCurso = findViewById<Button>(R.id.btnAgregarCurso)
        val btnFinalizarHV = findViewById<Button>(R.id.btnFinalizarHV)

        // Cargar datos previos
        if (DatosHojaVidaRepository.habilidadesPersonales.isNotEmpty()) etHabilidadesPersonales.setText(DatosHojaVidaRepository.habilidadesPersonales)
        if (DatosHojaVidaRepository.herramientas.isNotEmpty()) etHerramientas.setText(DatosHojaVidaRepository.herramientas)
        if (DatosHojaVidaRepository.idioma.isNotEmpty()) etIdioma.setText(DatosHojaVidaRepository.idioma)
        if (DatosHojaVidaRepository.nivelIdioma.isNotEmpty()) etNivelIdioma.setText(DatosHojaVidaRepository.nivelIdioma)
        if (DatosHojaVidaRepository.tipoLicencia.isNotEmpty()) etTipoLicencia.setText(DatosHojaVidaRepository.tipoLicencia)
        if (DatosHojaVidaRepository.referencias.isNotEmpty()) etReferencias.setText(DatosHojaVidaRepository.referencias)
        if (DatosHojaVidaRepository.logrosIA.isNotEmpty()) etLogros.setText(DatosHojaVidaRepository.logrosIA)

        btnAgregarCurso.setOnClickListener {
            startActivity(Intent(this, AgregarCursoActivity::class.java))
        }

        btnFinalizarHV.setOnClickListener {
            val hab = etHabilidadesPersonales.text.toString().trim()
            val herr = etHerramientas.text.toString().trim()
            val idioma = etIdioma.text.toString().trim()
            val nivelIdioma = etNivelIdioma.text.toString().trim()
            val ref = etReferencias.text.toString().trim()
            val logros = etLogros.text.toString().trim()

            if (hab.isEmpty()) {
                etHabilidadesPersonales.error = "Habilidades personales es obligatorio *"
                etHabilidadesPersonales.requestFocus()
                return@setOnClickListener
            }
            if (herr.isEmpty()) {
                etHerramientas.error = "Herramientas tecnológicas es obligatorio *"
                etHerramientas.requestFocus()
                return@setOnClickListener
            }
            if (idioma.isEmpty()) {
                etIdioma.error = "Idioma es obligatorio *"
                etIdioma.requestFocus()
                return@setOnClickListener
            }
            if (nivelIdioma.isEmpty()) {
                etNivelIdioma.error = "Nivel de idioma es obligatorio *"
                etNivelIdioma.requestFocus()
                return@setOnClickListener
            }
            if (ref.isEmpty()) {
                etReferencias.error = "Referencias laborales es obligatorio *"
                etReferencias.requestFocus()
                return@setOnClickListener
            }
            if (logros.isEmpty()) {
                etLogros.error = "Logros importantes es obligatorio *"
                etLogros.requestFocus()
                return@setOnClickListener
            }

            // Guardar datos completos de la hoja de vida
            DatosHojaVidaRepository.habilidadesPersonales = hab
            DatosHojaVidaRepository.herramientas = herr
            DatosHojaVidaRepository.idioma = idioma
            DatosHojaVidaRepository.nivelIdioma = nivelIdioma
            DatosHojaVidaRepository.tipoLicencia = etTipoLicencia.text.toString().trim()
            DatosHojaVidaRepository.referencias = ref
            DatosHojaVidaRepository.logrosIA = logros
            DatosHojaVidaRepository.guardarEnPrefs(this)

            Toast.makeText(this, "¡Hoja de Vida Creada Exitosamente!", Toast.LENGTH_LONG).show()

            // Navegamos a la pantalla de opciones de impresión/descarga
            startActivity(Intent(this, ImprimirHojaVidaActivity::class.java))
            finish()
        }
    }
}
