package com.example.app_empleate

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth

class AdminGestionUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_gestion_usuario)

        val btnAñadir = findViewById<MaterialButton>(R.id.btnAñadirUsuario)
        val btnEliminar = findViewById<MaterialButton>(R.id.btnEliminarUsuario)
        val btnMenu = findViewById<ImageView>(R.id.btnMenuGestionUsuario)
        val etNombre = findViewById<EditText>(R.id.etNombreGestion)
        val etCorreo = findViewById<EditText>(R.id.etCorreoGestion)
        val tvAdminEmailHeader = findViewById<TextView?>(R.id.tvAdminEmailHeader)

        val containerLogsAdmin = findViewById<LinearLayout>(R.id.containerLogsAdmin)

        // Mostrar correo de admin en encabezado
        val currentAdmin = FirebaseAuth.getInstance().currentUser
        val adminEmail = currentAdmin?.email ?: "admin@empleate.app"
        tvAdminEmailHeader?.text = adminEmail

        btnMenu.setOnClickListener {
            MenuNavegacionHelper.mostrarMenu(this)
        }

        fun renderizarLogs() {
            containerLogsAdmin.removeAllViews()
            for (log in AdminRegistroGlobalRepository.listaActividades) {
                val card = MaterialCardView(this).apply {
                    radius = 12f
                    cardElevation = 2f
                    setCardBackgroundColor(Color.parseColor("#EFF6FF"))
                    setStrokeColor(Color.parseColor("#DBEAFE"))
                    strokeWidth = 2
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(0, 0, 0, 12)
                    }
                    setLayoutParams(layoutParams)
                }

                val content = LinearLayout(this).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(16, 16, 16, 16)
                }

                val title = TextView(this).apply {
                    text = "[${log.tipo}] ${log.correo}"
                    setTextColor(Color.parseColor("#1E3A8A"))
                    textSize = 14f
                    typeface = android.graphics.Typeface.DEFAULT_BOLD
                }

                val detail = TextView(this).apply {
                    text = log.detalle
                    setTextColor(Color.parseColor("#334155"))
                    textSize = 13f
                }

                val date = TextView(this).apply {
                    text = log.fecha
                    setTextColor(Color.parseColor("#64748B"))
                    textSize = 11f
                }

                content.addView(title)
                content.addView(detail)
                content.addView(date)
                card.addView(content)
                containerLogsAdmin.addView(card)
            }
        }

        renderizarLogs()

        btnAñadir.setOnClickListener {
            val nom = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()

            if (nom.isEmpty() || correo.isEmpty()) {
                Toast.makeText(this, "Por favor completa nombre y correo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            AdminRegistroGlobalRepository.registrarUsuario(correo, nom, "Candidato Registrado por Admin")
            renderizarLogs()
            Toast.makeText(this, "Usuario $nom añadido exitosamente", Toast.LENGTH_SHORT).show()
            etNombre.setText("")
            etCorreo.setText("")
        }

        btnEliminar.setOnClickListener {
            val correo = etCorreo.text.toString().trim()
            if (correo.isEmpty()) {
                Toast.makeText(this, "Ingresa el correo para eliminar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            AdminRegistroGlobalRepository.registrarActividad("ADMIN", adminEmail, "Eliminó el usuario $correo")
            renderizarLogs()
            Toast.makeText(this, "Usuario $correo eliminado", Toast.LENGTH_SHORT).show()
            etCorreo.setText("")
        }
    }
}
