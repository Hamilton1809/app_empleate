package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class EmpresaSplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa_splash)

        findViewById<MaterialButton>(R.id.btnIngresarEmpresa).setOnClickListener {
            val intent = Intent(this, EmpresaLoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
