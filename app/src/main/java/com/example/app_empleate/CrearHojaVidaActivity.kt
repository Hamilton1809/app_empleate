package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CrearHojaVidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_hoja_vida)

        DatosHojaVidaRepository.cargarDePrefs(this)

        val btnMenuHV = findViewById<ImageView?>(R.id.btnMenuHV)
        val etHvNombre = findViewById<EditText>(R.id.etHvNombre)
        val etHvApellido = findViewById<EditText>(R.id.etHvApellido)
        val etHvCelular = findViewById<EditText>(R.id.etHvCelular)
        val etHvCorreo = findViewById<EditText>(R.id.etHvCorreo)
        val etHvCiudad = findViewById<EditText>(R.id.etHvCiudad)
        val etHvDepto = findViewById<EditText>(R.id.etHvDepto)
        val etHvProfesion = findViewById<EditText>(R.id.etHvProfesion)
        val etHvLinkedin = findViewById<EditText>(R.id.etHvLinkedin)
        val btnContinuar = findViewById<Button>(R.id.btnContinuarPaso2)

        // Cargar datos previos si existen
        if (DatosHojaVidaRepository.nombre.isNotEmpty()) etHvNombre.setText(DatosHojaVidaRepository.nombre)
        if (DatosHojaVidaRepository.apellido.isNotEmpty()) etHvApellido.setText(DatosHojaVidaRepository.apellido)
        if (DatosHojaVidaRepository.celular.isNotEmpty()) etHvCelular.setText(DatosHojaVidaRepository.celular)
        if (DatosHojaVidaRepository.correo.isNotEmpty()) etHvCorreo.setText(DatosHojaVidaRepository.correo)
        if (DatosHojaVidaRepository.ciudad.isNotEmpty()) etHvCiudad.setText(DatosHojaVidaRepository.ciudad)
        if (DatosHojaVidaRepository.departamento.isNotEmpty()) etHvDepto.setText(DatosHojaVidaRepository.departamento)
        if (DatosHojaVidaRepository.profesion.isNotEmpty()) etHvProfesion.setText(DatosHojaVidaRepository.profesion)
        if (DatosHojaVidaRepository.linkedin.isNotEmpty()) etHvLinkedin.setText(DatosHojaVidaRepository.linkedin)

        btnMenuHV?.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        btnContinuar.setOnClickListener {
            val nombre = etHvNombre.text.toString().trim()
            val apellido = etHvApellido.text.toString().trim()
            val celular = etHvCelular.text.toString().trim()
            val correo = etHvCorreo.text.toString().trim()
            val ciudad = etHvCiudad.text.toString().trim()
            val depto = etHvDepto.text.toString().trim()
            val profesion = etHvProfesion.text.toString().trim()

            if (nombre.isEmpty()) {
                etHvNombre.error = "Nombres es un campo obligatorio *"
                etHvNombre.requestFocus()
                return@setOnClickListener
            }
            if (apellido.isEmpty()) {
                etHvApellido.error = "Apellidos es un campo obligatorio *"
                etHvApellido.requestFocus()
                return@setOnClickListener
            }
            if (celular.isEmpty()) {
                etHvCelular.error = "Número de celular es un campo obligatorio *"
                etHvCelular.requestFocus()
                return@setOnClickListener
            }
            if (correo.isEmpty()) {
                etHvCorreo.error = "Correo electrónico es un campo obligatorio *"
                etHvCorreo.requestFocus()
                return@setOnClickListener
            }
            if (ciudad.isEmpty()) {
                etHvCiudad.error = "Ciudad es un campo obligatorio *"
                etHvCiudad.requestFocus()
                return@setOnClickListener
            }
            if (depto.isEmpty()) {
                etHvDepto.error = "Departamento es un campo obligatorio *"
                etHvDepto.requestFocus()
                return@setOnClickListener
            }
            if (profesion.isEmpty()) {
                etHvProfesion.error = "Profesión u ocupación es un campo obligatorio *"
                etHvProfesion.requestFocus()
                return@setOnClickListener
            }

            // Guardar en el repositorio
            DatosHojaVidaRepository.nombre = nombre
            DatosHojaVidaRepository.apellido = apellido
            DatosHojaVidaRepository.celular = celular
            DatosHojaVidaRepository.correo = correo
            DatosHojaVidaRepository.ciudad = ciudad
            DatosHojaVidaRepository.departamento = depto
            DatosHojaVidaRepository.profesion = profesion
            DatosHojaVidaRepository.linkedin = etHvLinkedin.text.toString().trim()
            DatosHojaVidaRepository.guardarEnPrefs(this)

            val intent = Intent(this, CrearHojaVidaPaso2Activity::class.java)
            startActivity(intent)
        }
    }
}
