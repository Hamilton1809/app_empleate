package com.example.app_empleate

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog

object SelectorCargoDialog {

    fun mostrarSelector(activity: Activity, onCargoSeleccionado: (String) -> Unit) {
        val dialog = BottomSheetDialog(activity)
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_selector_cargo, null)
        dialog.setContentView(view)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        view.findViewById<ImageView>(R.id.btnCerrarSelectorCargo)?.setOnClickListener {
            dialog.dismiss()
        }

        val containerCargos = view.findViewById<LinearLayout>(R.id.containerCargos)

        val cargos = arrayOf(
            "Auxiliar Administrativo",
            "Desarrollador Junior",
            "Atención al Cliente",
            "Vendedor / Comercial",
            "Contador / Finanzas",
            "Diseñador Gráfico",
            "Ingeniero de Sistemas",
            "Escribir otro cargo..."
        )

        containerCargos?.removeAllViews()

        for (i in cargos.indices) {
            val cargo = cargos[i]
            val itemView = LayoutInflater.from(activity).inflate(R.layout.item_selector_cargo, containerCargos, false)
            val tvNombre = itemView.findViewById<TextView>(R.id.tvNombreCargoItem)
            tvNombre.text = cargo

            itemView.setOnClickListener {
                dialog.dismiss()
                if (i == cargos.size - 1) {
                    val input = EditText(activity).apply {
                        hint = "Ej: Analista de datos"
                        setPadding(50, 40, 50, 40)
                    }
                    AlertDialog.Builder(activity)
                        .setTitle("Ingresa tu cargo objetivo")
                        .setView(input)
                        .setPositiveButton("Guardar") { _, _ ->
                            val nuevo = input.text.toString().trim()
                            if (nuevo.isNotEmpty()) {
                                PerfilUsuarioRepository.setCargoPostulacion(activity, nuevo)
                                onCargoSeleccionado(nuevo)
                            }
                        }
                        .setNegativeButton("Cancelar", null)
                        .show()
                } else {
                    PerfilUsuarioRepository.setCargoPostulacion(activity, cargo)
                    onCargoSeleccionado(cargo)
                }
            }
            containerCargos?.addView(itemView)
        }

        dialog.show()
    }
}
