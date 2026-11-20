package com.example.app_empleate

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ImprimirHojaVidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imprimir_hoja_vida)

        val btnMenuImprimir = findViewById<ImageView>(R.id.btnMenuImprimir)
        val btnCopiarLink = findViewById<MaterialButton>(R.id.btnCopiarLink)
        val btnDescargarPDF = findViewById<MaterialButton>(R.id.btnDescargarPDF)
        val btnDescargarWord = findViewById<MaterialButton>(R.id.btnDescargarWord)
        val btnImprimir = findViewById<MaterialButton>(R.id.btnImprimir)
        val btnIrEntrenamientosIA = findViewById<MaterialButton>(R.id.btnIrEntrenamientosIA)

        // Menú desplegable superior (☰)
        btnMenuImprimir.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menu.add(0, 1, 0, "Entrenamientos y tips IA")
            popup.menu.add(0, 2, 1, "Editar hoja de vida")
            popup.menu.add(0, 3, 2, "Inicio")

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    1 -> startActivity(Intent(this, EntrenamientosIAActivity::class.java))
                    2 -> startActivity(Intent(this, CrearHojaVidaActivity::class.java))
                    3 -> startActivity(Intent(this, MainActivity::class.java))
                }
                true
            }
            popup.show()
        }

        btnCopiarLink.setOnClickListener {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Enlace Hoja de Vida", "https://empleate.app/hv/mi-hoja-de-vida")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Enlace copiado al portapapeles", Toast.LENGTH_SHORT).show()
        }

        btnDescargarPDF.setOnClickListener {
            Toast.makeText(this, "Preparando descarga en PDF...", Toast.LENGTH_SHORT).show()
        }

        btnDescargarWord.setOnClickListener {
            Toast.makeText(this, "Preparando descarga en Word...", Toast.LENGTH_SHORT).show()
        }

        btnImprimir.setOnClickListener {
            Toast.makeText(this, "Abriendo vista previa para imprimir...", Toast.LENGTH_SHORT).show()
        }

        // Botón directo a Entrenamientos y tips IA
        btnIrEntrenamientosIA.setOnClickListener {
            val intent = Intent(this, EntrenamientosIAActivity::class.java)
            startActivity(intent)
        }
    }
}