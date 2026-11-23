package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class EmpresaPostulacionOfertasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa_postulacion_ofertas)

        val spCategorias = findViewById<Spinner>(R.id.spCategorias)
        val btnPublicarOferta = findViewById<MaterialButton>(R.id.btnPublicarOferta)

        val categorias = arrayOf("Administración", "Desarrollo de Software", "Atención al Cliente", "Ventas", "Marketing")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categorias)
        spCategorias.adapter = adapter

        btnPublicarOferta.setOnClickListener {
            Toast.makeText(this, "Oferta de Empleo Publicada Exitosamente", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, EmpresaHomeActivity::class.java))
            finish()
        }
    }
}
