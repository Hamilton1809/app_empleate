package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class RolesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roles)

        val btnRolUsuario = findViewById<MaterialButton>(R.id.btnRolUsuario)
        val btnRolEmpresa = findViewById<MaterialButton>(R.id.btnRolEmpresa)
        val btnRolAdmin = findViewById<MaterialButton>(R.id.btnRolAdmin)

        val cardRolUsuario = findViewById<MaterialCardView>(R.id.cardRolUsuario)
        val cardRolEmpresa = findViewById<MaterialCardView>(R.id.cardRolEmpresa)
        val cardRolAdmin = findViewById<MaterialCardView>(R.id.cardRolAdmin)

        val irAUsuario = {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val irAEmpresa = {
            startActivity(Intent(this, EmpresaSplashActivity::class.java))
        }

        val irAAdmin = {
            startActivity(Intent(this, AdminSplashActivity::class.java))
        }

        btnRolUsuario.setOnClickListener { irAUsuario() }
        cardRolUsuario.setOnClickListener { irAUsuario() }

        btnRolEmpresa.setOnClickListener { irAEmpresa() }
        cardRolEmpresa.setOnClickListener { irAEmpresa() }

        btnRolAdmin.setOnClickListener { irAAdmin() }
        cardRolAdmin.setOnClickListener { irAAdmin() }
    }
}
