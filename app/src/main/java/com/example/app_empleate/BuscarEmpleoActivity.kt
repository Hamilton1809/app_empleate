package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class BuscarEmpleoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buscar_empleo)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMenuBuscarEmpleo = findViewById<ImageView>(R.id.btnMenuBuscarEmpleo)
        val btnIrPostularOfertas = findViewById<MaterialButton>(R.id.btnIrPostularOfertas)
        val btnRegistrarseGratis = findViewById<MaterialButton>(R.id.btnRegistrarseGratis)
        val etQueEmpleo = findViewById<EditText>(R.id.etQueEmpleo)
        val etUbicacion = findViewById<EditText>(R.id.etUbicacion)
        val btnBuscar = findViewById<MaterialButton>(R.id.btnBuscar)

        // Lista completa de principales ciudades de Colombia
        val ciudadesColombia = arrayOf(
            "Popayán, Cauca",
            "Bogotá, D.C.",
            "Medellín, Antioquia",
            "Cali, Valle del Cauca",
            "Barranquilla, Atlántico",
            "Cartagena, Bolívar",
            "Bucaramanga, Santander",
            "Manizales, Caldas",
            "Pereira, Risaralda",
            "Cúcuta, Norte de Santander",
            "Ibagué, Tolima",
            "Pasto, Nariño",
            "Santa Marta, Magdalena",
            "Villavicencio, Meta",
            "Armenia, Quindío",
            "Montería, Córdoba",
            "Neiva, Huila",
            "Tunja, Boyacá",
            "Valledupar, Cesar",
            "Sincelejo, Sucre",
            "Riohacha, La Guajira"
        )

        fun mostrarSelectorCiudades() {
            AlertDialog.Builder(this)
                .setTitle("Selecciona una ciudad de Colombia")
                .setItems(ciudadesColombia) { _, which ->
                    etUbicacion.setText(ciudadesColombia[which])
                }
                .show()
        }

        etUbicacion.setOnClickListener { mostrarSelectorCiudades() }
        etUbicacion.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) mostrarSelectorCiudades()
        }

        // Menú desplegable superior (☰)
        btnMenuBuscarEmpleo?.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        btnIrPostularOfertas.setOnClickListener {
            startActivity(Intent(this, PostularOfertasActivity::class.java))
        }

        btnRegistrarseGratis.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        btnBuscar.setOnClickListener {
            val intent = Intent(this, PostularOfertasActivity::class.java)
            intent.putExtra("FILTRO_CARGO", etQueEmpleo.text.toString().trim())
            intent.putExtra("FILTRO_CIUDAD", etUbicacion.text.toString().trim())
            startActivity(intent)
        }
    }
}
