package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class RegistrarHojaVidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_hoja_vida)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMenuRegistroHV = findViewById<ImageView>(R.id.btnMenuRegistroHV)
        val etNombresHV = findViewById<EditText>(R.id.etNombresHV)
        val etApellidosHV = findViewById<EditText>(R.id.etApellidosHV)
        val etTipoDocumentoHV = findViewById<EditText>(R.id.etTipoDocumentoHV)
        val etTelefonoHV = findViewById<EditText>(R.id.etTelefonoHV)
        val etCorreoHV = findViewById<EditText>(R.id.etCorreoHV)
        val etPerfilProfesionalHV = findViewById<EditText>(R.id.etPerfilProfesionalHV)
        val etEstudiosFormalesHV = findViewById<EditText>(R.id.etEstudiosFormalesHV)
        val etExperienciaLaboralHV = findViewById<EditText>(R.id.etExperienciaLaboralHV)
        val etRefLaboralesHV = findViewById<EditText>(R.id.etRefLaboralesHV)
        val etRefFamiliaresHV = findViewById<EditText>(R.id.etRefFamiliaresHV)

        val btnCancelarRegistroHV = findViewById<MaterialButton>(R.id.btnCancelarRegistroHV)
        val btnGuardarCambiosHV = findViewById<MaterialButton>(R.id.btnGuardarCambiosHV)

        // Selector desplegable para Tipo de Documento
        val tiposDocumento = arrayOf(
            "Cédula de Ciudadanía (CC)",
            "Tarjeta de Identidad (TI)",
            "Cédula de Extranjería (CE)",
            "Pasaporte"
        )
        etTipoDocumentoHV.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Selecciona el tipo de documento")
                .setItems(tiposDocumento) { _, which ->
                    etTipoDocumentoHV.setText(tiposDocumento[which])
                }
                .show()
        }

        // Menú desplegable superior (☰)
        btnMenuRegistroHV?.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        btnCancelarRegistroHV.setOnClickListener {
            finish()
        }

        btnGuardarCambiosHV.setOnClickListener {
            val nombres = etNombresHV.text.toString().trim()
            val apellidos = etApellidosHV.text.toString().trim()
            val correo = etCorreoHV.text.toString().trim()

            if (nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty()) {
                Toast.makeText(
                    this,
                    "Completa al menos Nombres, Apellidos y Correo electrónico",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Hoja de vida guardada correctamente", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ImprimirHojaVidaActivity::class.java))
        }
    }
}