package com.example.app_empleate

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth

object MenuNavegacionHelper {

    fun mostrarMenu(activity: Activity) {
        val dialog = BottomSheetDialog(activity)
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_menu_navegacion, null)
        dialog.setContentView(view)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        view.findViewById<ImageView>(R.id.btnCerrarMenu)?.setOnClickListener {
            dialog.dismiss()
        }

        fun navegar(cls: Class<*>) {
            dialog.dismiss()
            if (activity.javaClass != cls) {
                activity.startActivity(Intent(activity, cls))
            }
        }

        fun configurarFila(
            container: LinearLayout?,
            texto: String,
            iconoRes: Int,
            visible: Boolean = true,
            onClick: () -> Unit
        ) {
            if (container == null) return
            if (!visible) {
                container.visibility = View.GONE
                return
            }
            container.visibility = View.VISIBLE
            val tv = container.getChildAt(1) as? TextView
            val img = container.getChildAt(0) as? ImageView
            tv?.text = texto
            img?.setImageResource(iconoRes)
            container.setOnClickListener { onClick() }
        }

        val esEmpresa = activity.javaClass.name.contains("Empresa")
        val esAdmin = activity.javaClass.name.contains("Admin")

        val tvMenuTitulo = view.findViewById<TextView?>(R.id.tvMenuTitulo)

        val btn1 = view.findViewById<LinearLayout>(R.id.btnMenuInicio)
        val btn2 = view.findViewById<LinearLayout>(R.id.btnMenuBuscar)
        val btn3 = view.findViewById<LinearLayout>(R.id.btnMenuOfertas)
        val btn4 = view.findViewById<LinearLayout>(R.id.btnMenuCrearHV)
        val btn5 = view.findViewById<LinearLayout>(R.id.btnMenuRegistrarHV)
        val btn6 = view.findViewById<LinearLayout>(R.id.btnMenuImprimirHV)
        val btn7 = view.findViewById<LinearLayout>(R.id.btnMenuIA)
        val btn8 = view.findViewById<LinearLayout>(R.id.btnMenuLogin)
        val btn9 = view.findViewById<LinearLayout>(R.id.btnMenuCerrarSesion)

        if (esEmpresa) {
            tvMenuTitulo?.text = "MENÚ EMPRESA"

            configurarFila(btn1, "Postulaciones / Inicio", android.R.drawable.ic_menu_today) { navegar(EmpresaHomeActivity::class.java) }
            configurarFila(btn2, "Postulación de Ofertas", android.R.drawable.ic_menu_edit) { navegar(EmpresaPostulacionOfertasActivity::class.java) }
            configurarFila(btn3, "Registrar Perfil de Empresa", android.R.drawable.ic_menu_save) { navegar(EmpresaRegistroActivity::class.java) }
            configurarFila(btn4, "Gestión de Finanzas", android.R.drawable.ic_menu_compass) { navegar(EmpresaFinanzasActivity::class.java) }
            configurarFila(btn5, "Recursos Humanos", android.R.drawable.ic_menu_myplaces) { navegar(EmpresaRecursosHumanosActivity::class.java) }
            configurarFila(btn6, "Control de Stock", android.R.drawable.ic_menu_agenda) { navegar(EmpresaControlStockActivity::class.java) }
            configurarFila(btn7, "Cambiar Contraseña", android.R.drawable.ic_lock_lock) { navegar(EmpresaCambiarPasswordActivity::class.java) }
            configurarFila(btn8, "Feedback y Comentarios", android.R.drawable.ic_menu_send) { navegar(EmpresaFeedbackActivity::class.java) }

        } else if (esAdmin) {
            tvMenuTitulo?.text = "MENÚ ADMINISTRATIVO"

            configurarFila(btn1, "Gestión de Empresa", android.R.drawable.ic_menu_manage) { navegar(AdminGestionEmpresaActivity::class.java) }
            configurarFila(btn2, "Gestión de Usuario", android.R.drawable.ic_menu_myplaces) { navegar(AdminGestionUsuarioActivity::class.java) }
            configurarFila(btn3, "Crear Categorías", android.R.drawable.ic_menu_add) { navegar(AdminCrearCategoriasActivity::class.java) }
            configurarFila(btn4, "Estrategia de Preparación", android.R.drawable.ic_menu_agenda) { navegar(AdminEstrategiaActivity::class.java) }
            configurarFila(btn5, "Cambiar Contraseña", android.R.drawable.ic_lock_lock) { navegar(AdminCambiarPasswordActivity::class.java) }
            configurarFila(btn6, "", 0, false) {}
            configurarFila(btn7, "", 0, false) {}
            configurarFila(btn8, "", 0, false) {}

        } else {
            // Usuario / Candidato por defecto
            tvMenuTitulo?.text = "MENÚ DE NAVEGACIÓN"

            val mostrarRegHv = activity !is RegistroActivity && activity !is RegistrarHojaVidaActivity

            configurarFila(btn1, "Inicio", android.R.drawable.ic_menu_myplaces) { navegar(MainActivity::class.java) }
            configurarFila(btn2, "Buscar empleo", android.R.drawable.ic_menu_search) { navegar(BuscarEmpleoActivity::class.java) }
            configurarFila(btn3, "Postular Ofertas", android.R.drawable.ic_menu_agenda) { navegar(PostularOfertasActivity::class.java) }
            configurarFila(btn4, "Crear / Editar Hoja de Vida", android.R.drawable.ic_menu_edit) { navegar(CrearHojaVidaActivity::class.java) }
            configurarFila(btn5, "Registrar Hoja de Vida", android.R.drawable.ic_menu_save, mostrarRegHv) { navegar(RegistrarHojaVidaActivity::class.java) }
            configurarFila(btn6, "Imprimir / Descargar Hoja de Vida", android.R.drawable.ic_menu_share) { navegar(ImprimirHojaVidaActivity::class.java) }
            configurarFila(btn7, "Entrenamientos y tips IA", android.R.drawable.ic_menu_manage) { navegar(EntrenamientosIAActivity::class.java) }
            configurarFila(btn8, "Iniciar Sesión / Registro", android.R.drawable.ic_lock_lock) { navegar(LoginActivity::class.java) }
        }

        // Fila 9: Cerrar sesión
        configurarFila(btn9, "Cerrar Sesión", android.R.drawable.ic_lock_power_off) {
            dialog.dismiss()
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(activity, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, RolesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            activity.startActivity(intent)
            activity.finish()
        }

        dialog.show()
    }
}
