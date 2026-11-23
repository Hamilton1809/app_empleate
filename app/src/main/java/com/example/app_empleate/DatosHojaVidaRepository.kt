package com.example.app_empleate

import android.content.Context
import android.content.SharedPreferences

object DatosHojaVidaRepository {
    private const val PREFS_NAME = "prefs_hoja_vida"

    var nombre: String = ""
    var apellido: String = ""
    var celular: String = ""
    var correo: String = ""
    var ciudad: String = ""
    var departamento: String = ""
    var profesion: String = ""
    var linkedin: String = ""

    var cargoDeseado: String = ""
    var anosExperiencia: String = ""
    var areasExperiencia: String = ""
    var fortalezas: String = ""
    var caracteristicas: String = ""

    var habilidadesPersonales: String = ""
    var herramientas: String = ""
    var idioma: String = ""
    var nivelIdioma: String = ""
    var tipoLicencia: String = ""
    var referencias: String = ""
    var logrosIA: String = ""

    fun guardarEnPrefs(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString("nombre", nombre)
            putString("apellido", apellido)
            putString("celular", celular)
            putString("correo", correo)
            putString("ciudad", ciudad)
            putString("departamento", departamento)
            putString("profesion", profesion)
            putString("linkedin", linkedin)
            putString("cargoDeseado", cargoDeseado)
            putString("anosExperiencia", anosExperiencia)
            putString("areasExperiencia", areasExperiencia)
            putString("fortalezas", fortalezas)
            putString("caracteristicas", caracteristicas)
            putString("habilidadesPersonales", habilidadesPersonales)
            putString("herramientas", herramientas)
            putString("idioma", idioma)
            putString("nivelIdioma", nivelIdioma)
            putString("tipoLicencia", tipoLicencia)
            putString("referencias", referencias)
            putString("logrosIA", logrosIA)
            apply()
        }
    }

    fun cargarDePrefs(context: Context) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        nombre = prefs.getString("nombre", "").orEmpty()
        apellido = prefs.getString("apellido", "").orEmpty()
        celular = prefs.getString("celular", "").orEmpty()
        correo = prefs.getString("correo", "").orEmpty()
        ciudad = prefs.getString("ciudad", "").orEmpty()
        departamento = prefs.getString("departamento", "").orEmpty()
        profesion = prefs.getString("profesion", "").orEmpty()
        linkedin = prefs.getString("linkedin", "").orEmpty()
        cargoDeseado = prefs.getString("cargoDeseado", "").orEmpty()
        anosExperiencia = prefs.getString("anosExperiencia", "").orEmpty()
        areasExperiencia = prefs.getString("areasExperiencia", "").orEmpty()
        fortalezas = prefs.getString("fortalezas", "").orEmpty()
        caracteristicas = prefs.getString("caracteristicas", "").orEmpty()
        habilidadesPersonales = prefs.getString("habilidadesPersonales", "").orEmpty()
        herramientas = prefs.getString("herramientas", "").orEmpty()
        idioma = prefs.getString("idioma", "").orEmpty()
        nivelIdioma = prefs.getString("nivelIdioma", "").orEmpty()
        tipoLicencia = prefs.getString("tipoLicencia", "").orEmpty()
        referencias = prefs.getString("referencias", "").orEmpty()
        logrosIA = prefs.getString("logrosIA", "").orEmpty()
    }
}
