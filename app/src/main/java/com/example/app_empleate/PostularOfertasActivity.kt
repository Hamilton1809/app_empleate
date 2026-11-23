package com.example.app_empleate

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class PostularOfertasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_postular_ofertas)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnMenuOfertas = findViewById<ImageView>(R.id.btnMenuOfertas)
        val layoutSinResultados = findViewById<LinearLayout>(R.id.layoutSinResultados)
        val btnVerTodasOfertas = findViewById<MaterialButton>(R.id.btnVerTodasOfertas)

        val cardOferta1 = findViewById<MaterialCardView>(R.id.cardOferta1)
        val cardOferta2 = findViewById<MaterialCardView>(R.id.cardOferta2)
        val cardOferta3 = findViewById<MaterialCardView>(R.id.cardOferta3)

        val btnPostular1 = findViewById<MaterialButton>(R.id.btnPostular1)
        val btnPostular2 = findViewById<MaterialButton>(R.id.btnPostular2)
        val btnPostular3 = findViewById<MaterialButton>(R.id.btnPostular3)

        // Filtrado automático si el usuario viene desde BuscarEmpleoActivity
        val filtroCargo = intent.getStringExtra("FILTRO_CARGO")?.lowercase().orEmpty()
        val filtroCiudad = intent.getStringExtra("FILTRO_CIUDAD")?.lowercase().orEmpty()

        if (filtroCargo.isNotEmpty() || filtroCiudad.isNotEmpty()) {
            val coincide1 = (filtroCargo.isEmpty() || "auxiliar administrativo".contains(filtroCargo)) &&
                    (filtroCiudad.isEmpty() || "popayán popayan cauca".contains(filtroCiudad))
            val coincide2 = (filtroCargo.isEmpty() || "desarrollador junior".contains(filtroCargo)) &&
                    (filtroCiudad.isEmpty() || "cali valle".contains(filtroCiudad))
            val coincide3 = (filtroCargo.isEmpty() || "atención atencion al cliente".contains(filtroCargo)) &&
                    (filtroCiudad.isEmpty() || "bogotá bogota".contains(filtroCiudad))

            cardOferta1.visibility = if (coincide1) View.VISIBLE else View.GONE
            cardOferta2.visibility = if (coincide2) View.VISIBLE else View.GONE
            cardOferta3.visibility = if (coincide3) View.VISIBLE else View.GONE

            if (!coincide1 && !coincide2 && !coincide3) {
                layoutSinResultados.visibility = View.VISIBLE
            }
        }

        btnVerTodasOfertas.setOnClickListener {
            layoutSinResultados.visibility = View.GONE
            cardOferta1.visibility = View.VISIBLE
            cardOferta2.visibility = View.VISIBLE
            cardOferta3.visibility = View.VISIBLE
        }

        // Menú desplegable superior (☰)
        btnMenuOfertas?.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        btnPostular1.setOnClickListener {
            confirmarPostulacion("Auxiliar Administrativo", "Soluciones S.A.", btnPostular1)
        }

        btnPostular2.setOnClickListener {
            confirmarPostulacion("Desarrollador Junior", "TecnoSoft", btnPostular2)
        }

        btnPostular3.setOnClickListener {
            confirmarPostulacion("Atención al Cliente", "GlobalService", btnPostular3)
        }
    }

    private fun confirmarPostulacion(cargo: String, empresa: String, boton: MaterialButton) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar postulación")
            .setMessage("¿Deseas enviar tu hoja de vida al cargo de $cargo en $empresa?")
            .setPositiveButton("Sí, postularme") { _, _ ->
                boton.text = "¡Postulado!"
                boton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1E3A8A"))
                boton.isEnabled = false
                Toast.makeText(this, "Postulación enviada a $empresa", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}