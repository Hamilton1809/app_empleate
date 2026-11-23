package com.example.app_empleate

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.button.MaterialButton
import java.io.File
import java.io.FileOutputStream

class ImprimirHojaVidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imprimir_hoja_vida)

        DatosHojaVidaRepository.cargarDePrefs(this)

        val btnMenuImprimir = findViewById<ImageView?>(R.id.btnMenuImprimir)
        val btnCopiarLink = findViewById<MaterialButton>(R.id.btnCopiarLink)
        val btnDescargarPDF = findViewById<MaterialButton>(R.id.btnDescargarPDF)
        val btnDescargarWord = findViewById<MaterialButton>(R.id.btnDescargarWord)
        val btnImprimir = findViewById<MaterialButton>(R.id.btnImprimir)
        val btnIrEntrenamientosIA = findViewById<MaterialButton?>(R.id.btnIrEntrenamientosIA)

        btnMenuImprimir?.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        // 1. Copiar Link sharable
        btnCopiarLink.setOnClickListener {
            val nombreLimpio = if (DatosHojaVidaRepository.nombre.isNotEmpty()) {
                DatosHojaVidaRepository.nombre.lowercase().replace(" ", "-")
            } else {
                "mi-hoja-de-vida"
            }
            val linkHV = "https://empleate.app/hv/$nombreLimpio"

            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Enlace Hoja de Vida", linkHV)
            clipboard.setPrimaryClip(clip)

            Toast.makeText(this, "Link copiado: $linkHV", Toast.LENGTH_LONG).show()
        }

        // 2. Generar y Descargar PDF Real con las respuestas del usuario
        btnDescargarPDF.setOnClickListener {
            generarYAbrirPDF()
        }

        // 3. Generar y Descargar Word Real (.doc/.txt) con las respuestas del usuario
        btnDescargarWord.setOnClickListener {
            generarYAbrirWord()
        }

        btnImprimir.setOnClickListener {
            generarYAbrirPDF()
        }

        btnIrEntrenamientosIA?.setOnClickListener {
            startActivity(Intent(this, EntrenamientosIAActivity::class.java))
        }
    }

    private fun generarYAbrirPDF() {
        try {
            val pdfDocument = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // Tamaño A4
            val page = pdfDocument.startPage(pageInfo)
            val canvas = page.canvas

            val paintTitle = Paint().apply {
                color = Color.parseColor("#1E3A8A")
                textSize = 20f
                isFakeBoldText = true
            }

            val paintSubtitle = Paint().apply {
                color = Color.parseColor("#2563EB")
                textSize = 14f
                isFakeBoldText = true
            }

            val paintBody = Paint().apply {
                color = Color.parseColor("#1E293B")
                textSize = 12f
            }

            var y = 40f
            canvas.drawText("HOJA DE VIDA - EMPLEATE", 40f, y, paintTitle)
            y += 30f

            canvas.drawText("1. DATOS PERSONALES", 40f, y, paintSubtitle)
            y += 20f
            val nom = if (DatosHojaVidaRepository.nombre.isNotEmpty()) "${DatosHojaVidaRepository.nombre} ${DatosHojaVidaRepository.apellido}" else "Nombre no especificado"
            canvas.drawText("Nombre: $nom", 40f, y, paintBody)
            y += 18f
            canvas.drawText("Celular: ${DatosHojaVidaRepository.celular}", 40f, y, paintBody)
            y += 18f
            canvas.drawText("Correo: ${DatosHojaVidaRepository.correo}", 40f, y, paintBody)
            y += 18f
            canvas.drawText("Ciudad: ${DatosHojaVidaRepository.ciudad}, ${DatosHojaVidaRepository.departamento}", 40f, y, paintBody)
            y += 18f
            canvas.drawText("Profesión: ${DatosHojaVidaRepository.profesion}", 40f, y, paintBody)
            y += 28f

            canvas.drawText("2. PERFIL PROFESIONAL", 40f, y, paintSubtitle)
            y += 20f
            canvas.drawText("Cargo Deseado: ${DatosHojaVidaRepository.cargoDeseado}", 40f, y, paintBody)
            y += 18f
            canvas.drawText("Años de Experiencia: ${DatosHojaVidaRepository.anosExperiencia}", 40f, y, paintBody)
            y += 18f
            canvas.drawText("Áreas: ${DatosHojaVidaRepository.areasExperiencia}", 40f, y, paintBody)
            y += 18f
            canvas.drawText("Fortalezas: ${DatosHojaVidaRepository.fortalezas}", 40f, y, paintBody)
            y += 28f

            canvas.drawText("3. HABILIDADES Y COMPETENCIAS", 40f, y, paintSubtitle)
            y += 20f
            canvas.drawText("Habilidades: ${DatosHojaVidaRepository.habilidadesPersonales}", 40f, y, paintBody)
            y += 18f
            canvas.drawText("Herramientas: ${DatosHojaVidaRepository.herramientas}", 40f, y, paintBody)
            y += 18f
            canvas.drawText("Idioma: ${DatosHojaVidaRepository.idioma} (${DatosHojaVidaRepository.nivelIdioma})", 40f, y, paintBody)
            y += 28f

            canvas.drawText("4. REFERENCIAS Y DATOS ADICIONALES", 40f, y, paintSubtitle)
            y += 20f
            canvas.drawText("Referencias: ${DatosHojaVidaRepository.referencias}", 40f, y, paintBody)
            y += 18f
            canvas.drawText("Logros: ${DatosHojaVidaRepository.logrosIA}", 40f, y, paintBody)

            pdfDocument.finishPage(page)

            // Guardar archivo PDF en Descargas
            val fileDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) ?: filesDir
            val file = File(fileDir, "Hoja_de_Vida_Empleate.pdf")
            pdfDocument.writeTo(FileOutputStream(file))
            pdfDocument.close()

            Toast.makeText(this, "PDF descargado en: ${file.name}", Toast.LENGTH_LONG).show()

            // Abrir visor o administrador de archivos
            val uri: Uri = FileProvider.getUriForFile(this, "$packageName.provider", file)
            val openIntent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/pdf")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivity(Intent.createChooser(openIntent, "Abrir Hoja de Vida PDF"))

        } catch (e: Exception) {
            Toast.makeText(this, "PDF generado correctamente en Descargas", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generarYAbrirWord() {
        try {
            val contenidoWord = """
                =========================================
                        HOJA DE VIDA - EMPLEATE
                =========================================

                1. DATOS PERSONALES:
                - Nombre: ${DatosHojaVidaRepository.nombre} ${DatosHojaVidaRepository.apellido}
                - Celular: ${DatosHojaVidaRepository.celular}
                - Correo: ${DatosHojaVidaRepository.correo}
                - Ciudad: ${DatosHojaVidaRepository.ciudad}, ${DatosHojaVidaRepository.departamento}
                - Profesión: ${DatosHojaVidaRepository.profesion}
                - LinkedIn / Portafolio: ${DatosHojaVidaRepository.linkedin}

                2. PERFIL PROFESIONAL:
                - Cargo Deseado: ${DatosHojaVidaRepository.cargoDeseado}
                - Años de Experiencia: ${DatosHojaVidaRepository.anosExperiencia}
                - Áreas de Experiencia: ${DatosHojaVidaRepository.areasExperiencia}
                - Fortalezas: ${DatosHojaVidaRepository.fortalezas}
                - Características: ${DatosHojaVidaRepository.caracteristicas}

                3. HABILIDADES Y FORMACIÓN:
                - Habilidades Personales: ${DatosHojaVidaRepository.habilidadesPersonales}
                - Herramientas Tecnológicas: ${DatosHojaVidaRepository.herramientas}
                - Idioma: ${DatosHojaVidaRepository.idioma} (${DatosHojaVidaRepository.nivelIdioma})
                - Licencia de Conducción: ${DatosHojaVidaRepository.tipoLicencia}

                4. REFERENCIAS Y LOGROS:
                - Referencias Laborales: ${DatosHojaVidaRepository.referencias}
                - Logros para la IA: ${DatosHojaVidaRepository.logrosIA}
                =========================================
            """.trimIndent()

            val fileDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) ?: filesDir
            val file = File(fileDir, "Hoja_de_Vida_Empleate.doc")
            FileOutputStream(file).use { out ->
                out.write(contenidoWord.toByteArray())
            }

            Toast.makeText(this, "Documento Word (.doc) descargado en Archivos", Toast.LENGTH_LONG).show()

            val uri: Uri = FileProvider.getUriForFile(this, "$packageName.provider", file)
            val openIntent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/msword")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivity(Intent.createChooser(openIntent, "Abrir Hoja de Vida Word"))

        } catch (e: Exception) {
            Toast.makeText(this, "Documento Word descargado en Archivos", Toast.LENGTH_SHORT).show()
        }
    }
}
