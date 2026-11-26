package com.example.app_empleate

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class EmpresaHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_empresa_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMenuEmpresa = findViewById<ImageView>(R.id.btnMenuEmpresa)
        val tvEmpresaEmailHeader = findViewById<TextView?>(R.id.tvEmpresaEmailHeader)

        val currentEmpresa = FirebaseAuth.getInstance().currentUser
        val empresaEmail = currentEmpresa?.email ?: "empresa@empleate.app"
        tvEmpresaEmailHeader?.text = empresaEmail

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
