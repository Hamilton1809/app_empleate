package com.example.app_empleate // Verifica tu paquete

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class CrearHojaVidaPaso2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_hoja_vida_paso2)

        // 1. Enlazamos los elementos de Experiencia Laboral
        val rgExperiencia = findViewById<RadioGroup>(R.id.rgExperiencia)
        val rbSiExperiencia = findViewById<RadioButton>(R.id.rbSiExperiencia)
        val rbNoExperiencia = findViewById<RadioButton>(R.id.rbNoExperiencia)
        val btnAgregarExperiencia = findViewById<Button>(R.id.btnAgregarExperiencia)

        // 2. Enlazamos los otros botones
        val btnAgregarEstudio = findViewById<Button>(R.id.btnAgregarEstudio)
        val btnContinuarPaso3 = findViewById<Button>(R.id.btnContinuarPaso3)

        // Lógica inicial: Desactivar botón de experiencia por defecto hasta que seleccione "Sí"
        btnAgregarExperiencia.isEnabled = false
        btnAgregarExperiencia.alpha = 0.5f // Hacerlo ver un poco transparente (desactivado)

        // 3. Lógica para habilitar/deshabilitar el botón según lo que marque
        rgExperiencia.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbSiExperiencia) {
                // Seleccionó "Sí" -> Habilitar botón
                btnAgregarExperiencia.isEnabled = true
                btnAgregarExperiencia.alpha = 1.0f
            } else if (checkedId == R.id.rbNoExperiencia) {
                // Seleccionó "No" -> Deshabilitar botón
                btnAgregarExperiencia.isEnabled = false
                btnAgregarExperiencia.alpha = 0.5f
            }
        }

        // 4. Ir a la pantalla de Agregar Experiencia
        btnAgregarExperiencia.setOnClickListener {
            val intent = Intent(this, AgregarExperienciaActivity::class.java)
            startActivity(intent)
        }

        // 5. Ir a la pantalla de Agregar Estudio
        btnAgregarEstudio.setOnClickListener {
            val intent = Intent(this, AgregarEstudioActivity::class.java)
            startActivity(intent)
        }

        // 6. Botón continuar (al Paso 3)
        btnContinuarPaso3.setOnClickListener {
            val intent = Intent(this, CrearHojaVidaPaso3Activity::class.java)
            startActivity(intent)

        }
    }
}