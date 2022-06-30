package com.example.tp2empresatelefonica.repositorios

import com.example.tp2empresatelefonica.clases.usuarios.TipoUsuario
import com.example.tp2empresatelefonica.clases.usuarios.Usuario

object UsersRepository {

    val listaDeUsuarios = mutableListOf<Usuario>()

    init {

        listaDeUsuarios.addAll(
            listOf(

                Usuario(0, "a", "a", TipoUsuario.ADMINISTRADOR),
                Usuario(1, "c1", "c1", TipoUsuario.CLIENTE),
                Usuario(2, "c2", "c2", TipoUsuario.CLIENTE),
                Usuario(3, "c3", "c3", TipoUsuario.CLIENTE),
                Usuario(4, "c4", "c4", TipoUsuario.CLIENTE),
                Usuario(5, "c5", "c5", TipoUsuario.CLIENTE),
                Usuario(6, "c6", "c6", TipoUsuario.CLIENTE),
                Usuario(7, "c7", "c7", TipoUsuario.CLIENTE),
                Usuario(123, "c8", "c8", TipoUsuario.CLIENTE),
                Usuario(234, "c9", "c9", TipoUsuario.CLIENTE),
                Usuario(8, "c10", "c10", TipoUsuario.CLIENTE),
                Usuario(9, "c11", "c11", TipoUsuario.CLIENTE),
                Usuario(900, "c12", "c12", TipoUsuario.CLIENTE),
                Usuario(750, "c13", "c13", TipoUsuario.CLIENTE),
                Usuario(10, "c14", "c14", TipoUsuario.CLIENTE),
                Usuario(11, "c15", "c15", TipoUsuario.CLIENTE)
            )
        )
    }

    fun obtenerUsuario(id: Int): Usuario? {
        listaDeUsuarios.forEach { usuario ->
            if (usuario.idUsuario == id) {
                return usuario
            }
        }
        return null
    }

    fun obtenerUsuario(nombre: String, clave: String): Usuario? {
        listaDeUsuarios.forEach { usuario ->
            if (usuario.user == nombre && usuario.password == clave) {
                return usuario
            }
        }
        return null
    }

    fun obtenerListaUsuarios(): MutableList<Usuario> {

        return listaDeUsuarios
    }

    fun removerUsuario(id: Int) {

        listaDeUsuarios.remove(obtenerUsuario(id))

    }

    fun agregarUsuario(id: Int, nombre: String, password: String, tipoDeUsuario: TipoUsuario) {

        listaDeUsuarios.add(Usuario(id, nombre, password, tipoDeUsuario))
    }
}