package com.example.app_empleate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class AdminSplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_splash)

        findViewById<MaterialButton>(R.id.btnIngresarAdmin).setOnClickListener {
            val intent = Intent(this, AdminCrearCategoriasActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
