package com.example.app_empleate

object AdminRegistroGlobalRepository {

    data class ActividadLog(
        val tipo: String, // "USUARIO", "EMPRESA", "POSTULACION"
        val correo: String,
        val detalle: String,
        val fecha: String
    )

    val listaActividades = mutableListOf<ActividadLog>(
        ActividadLog("USUARIO", "candidato.juan@gmail.com", "Creó Hoja de Vida para Desarrollador Junior", "Hoy, 10:15 AM"),
        ActividadLog("EMPRESA", "rrhh@tecnosoft.com", "Registró perfil de Empresa TecnoSoft S.A.", "Hoy, 09:30 AM"),
        ActividadLog("POSTULACION", "candidato.juan@gmail.com", "Se postuló a la vacante Desarrollador Junior en TecnoSoft", "Hoy, 10:20 AM"),
        ActividadLog("USUARIO", "maria.perez@gmail.com", "Creó Hoja de Vida para Auxiliar Administrativo", "Ayer, 04:45 PM"),
        ActividadLog("EMPRESA", "contacto@soluciones.com", "Publicó oferta Auxiliar Administrativo", "Ayer, 02:10 PM"),
        ActividadLog("POSTULACION", "maria.perez@gmail.com", "Se postuló a Auxiliar Administrativo en Soluciones S.A.", "Ayer, 05:00 PM")
    )

    val listaUsuarios = mutableListOf<String>(
        "candidato.juan@gmail.com - Juan Pérez (Desarrollador Junior)",
        "maria.perez@gmail.com - María Pérez (Auxiliar Administrativo)",
        "carlos.gomez@gmail.com - Carlos Gómez (Atención al Cliente)",
        "ana.rodriguez@gmail.com - Ana Rodríguez (Contadora)"
    )

    val listaEmpresas = mutableListOf<String>(
        "rrhh@tecnosoft.com - TecnoSoft S.A. (Cali)",
        "contacto@soluciones.com - Soluciones S.A. (Popayán)",
        "empleos@globalservice.com - GlobalService (Bogotá)"
    )

    fun registrarActividad(tipo: String, correo: String, detalle: String) {
        listaActividades.add(0, ActividadLog(tipo, correo, detalle, "Justo ahora"))
    }

    fun registrarUsuario(correo: String, nombre: String, cargo: String) {
        val entry = "$correo - $nombre ($cargo)"
        if (!listaUsuarios.contains(entry)) {
            listaUsuarios.add(0, entry)
        }
        registrarActividad("USUARIO", correo, "Registró usuario y Hoja de Vida ($cargo)")
    }

    fun registrarEmpresa(correo: String, nombreEmpresa: String, ciudad: String) {
        val entry = "$correo - $nombreEmpresa ($ciudad)"
        if (!listaEmpresas.contains(entry)) {
            listaEmpresas.add(0, entry)
        }
        registrarActividad("EMPRESA", correo, "Registró nueva empresa $nombreEmpresa en $ciudad")
    }

    fun registrarPostulacion(correo: String, cargo: String, empresa: String) {
        registrarActividad("POSTULACION", correo, "Se postuló a $cargo en $empresa")
    }
}
