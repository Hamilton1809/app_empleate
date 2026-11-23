package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class EmpresaHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa_home)

        val btnMenuEmpresa = findViewById<ImageView>(R.id.btnMenuEmpresa)

        val btnVerPostuladosAuxiliar = findViewById<MaterialButton>(R.id.btnVerPostuladosAuxiliar)
        val btnVerPostuladosDev = findViewById<MaterialButton>(R.id.btnVerPostuladosDev)
        val btnVerPostuladosCliente = findViewById<MaterialButton>(R.id.btnVerPostuladosCliente)

        // Menú desplegable superior (☰) interactivo para Empresa
        btnMenuEmpresa.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        // Listeners para las tarjetas de vacantes
        btnVerPostuladosAuxiliar.setOnClickListener {
            Toast.makeText(this, "Viendo candidatos para Auxiliar Administrativo", Toast.LENGTH_SHORT).show()
        }

        btnVerPostuladosDev.setOnClickListener {
            Toast.makeText(this, "Viendo candidatos para Desarrollador Junior", Toast.LENGTH_SHORT).show()
        }

        btnVerPostuladosCliente.setOnClickListener {
            Toast.makeText(this, "Viendo candidatos para Atención al Cliente", Toast.LENGTH_SHORT).show()
        }
    }
}
