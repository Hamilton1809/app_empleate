package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView

class EmpresaOpcionesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_empresa_opciones)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRegresarOpciones = findViewById<ImageView>(R.id.btnRegresarOpciones)
        val btnMenuOpciones = findViewById<ImageView>(R.id.btnMenuOpciones)

        val cardOpcionInicio = findViewById<MaterialCardView>(R.id.cardOpcionInicio)
        val cardOpcionPublicar = findViewById<MaterialCardView>(R.id.cardOpcionPublicar)
        val cardOpcionAgenda = findViewById<MaterialCardView>(R.id.cardOpcionAgenda)
        val cardOpcionPerfil = findViewById<MaterialCardView>(R.id.cardOpcionPerfil)
        val cardOpcionFeedback = findViewById<MaterialCardView>(R.id.cardOpcionFeedback)

        btnRegresarOpciones.setOnClickListener { finish() }
        btnMenuOpciones.setOnClickListener { MenuNavegacionHelper.mostrarMenu(this) }

        cardOpcionInicio.setOnClickListener {
            startActivity(Intent(this, EmpresaHomeActivity::class.java))
        }

        cardOpcionPublicar.setOnClickListener {
            startActivity(Intent(this, EmpresaPostulacionOfertasActivity::class.java))
        }

        cardOpcionAgenda.setOnClickListener {
            Toast.makeText(this, "Sección Agenda de Entrevistas", Toast.LENGTH_SHORT).show()
        }

        cardOpcionPerfil.setOnClickListener {
            startActivity(Intent(this, EmpresaRegistroActivity::class.java))
        }

        cardOpcionFeedback.setOnClickListener {
            startActivity(Intent(this, EmpresaFeedbackActivity::class.java))
        }
    }
}
