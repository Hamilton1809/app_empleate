package com.example.app_empleate

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth

object MenuNavegacionHelper {

    fun mostrarMenu(activity: Activity) {
        val dialog = BottomSheetDialog(activity)
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_menu_navegacion, null)
        dialog.setContentView(view)

        // Fondo transparente para ver las esquinas redondeadas del menú
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        view.findViewById<ImageView>(R.id.btnCerrarMenu)?.setOnClickListener {
            dialog.dismiss()
        }

        // Si ya estamos en la pantalla de datos personales/registro, ocultamos esa opción del menú
        if (activity is RegistroActivity || activity is RegistrarHojaVidaActivity) {
            view.findViewById<View>(R.id.btnMenuRegistrarHV)?.visibility = View.GONE
        }

        fun navegar(cls: Class<*>) {
            dialog.dismiss()
            if (activity.javaClass != cls) {
                activity.startActivity(Intent(activity, cls))
            }
        }

        view.findViewById<View>(R.id.btnMenuInicio)?.setOnClickListener { navegar(MainActivity::class.java) }
        view.findViewById<View>(R.id.btnMenuBuscar)?.setOnClickListener { navegar(BuscarEmpleoActivity::class.java) }
        view.findViewById<View>(R.id.btnMenuOfertas)?.setOnClickListener { navegar(PostularOfertasActivity::class.java) }
        view.findViewById<View>(R.id.btnMenuCrearHV)?.setOnClickListener { navegar(CrearHojaVidaActivity::class.java) }
        view.findViewById<View>(R.id.btnMenuRegistrarHV)?.setOnClickListener { navegar(RegistrarHojaVidaActivity::class.java) }
        view.findViewById<View>(R.id.btnMenuImprimirHV)?.setOnClickListener { navegar(ImprimirHojaVidaActivity::class.java) }
        view.findViewById<View>(R.id.btnMenuIA)?.setOnClickListener { navegar(EntrenamientosIAActivity::class.java) }
        view.findViewById<View>(R.id.btnMenuLogin)?.setOnClickListener { navegar(LoginActivity::class.java) }

        // Cerrar sesión y redirigir a LoginActivity
        view.findViewById<View>(R.id.btnMenuCerrarSesion)?.setOnClickListener {
            dialog.dismiss()
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(activity, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            activity.startActivity(intent)
            activity.finish()
        }

        dialog.show()
    }
}
