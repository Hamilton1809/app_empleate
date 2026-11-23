package com.example.app_empleate

import android.content.Context
import android.content.SharedPreferences

object PerfilUsuarioRepository {
    private const val PREFS_NAME = "prefs_perfil_usuario"
    private const val KEY_CARGO_POSTULACION = "cargo_postulacion"

    fun getCargoPostulacion(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val cargo = prefs.getString(KEY_CARGO_POSTULACION, "")
        if (!cargo.isNullOrEmpty()) {
            return cargo
        }
        // Si ya llenó la Hoja de Vida, usamos su cargo deseado
        if (DatosHojaVidaRepository.cargoDeseado.isNotEmpty()) {
            return DatosHojaVidaRepository.cargoDeseado
        }
        return "Desarrollador / Profesional General"
    }

    fun setCargoPostulacion(context: Context, cargo: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_CARGO_POSTULACION, cargo).apply()
        DatosHojaVidaRepository.cargoDeseado = cargo
    }
}
