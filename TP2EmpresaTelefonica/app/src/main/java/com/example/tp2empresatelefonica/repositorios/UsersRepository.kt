package com.example.tp2empresatelefonica.repositorios

import com.example.tp2empresatelefonica.clases.usuarios.TipoUsuario
import com.example.tp2empresatelefonica.clases.usuarios.Usuario
import com.example.tp2empresatelefonica.excepciones.NoExisteUsuario

object UsersRepository {

    val listaDeUsuarios = mutableListOf<Usuario>()

    init {

        listaDeUsuarios.addAll(listOf(

            Usuario(1,"a","a",TipoUsuario.ADMINISTRADOR),
            Usuario(2,"c","c",TipoUsuario.CLIENTE)

        ))
    }

    fun obtenerUsuario(id : Int) : Usuario {
        listaDeUsuarios.forEach {
            usuario ->
            if(usuario.idUsuario == id) {
                return usuario
            }
        }
        throw NoExisteUsuario("[ERROR] Usuario inexistente.")
    }

    fun obtenerListaUsuarios() : MutableList<Usuario>{

        return listaDeUsuarios
    }

    fun removerUsuario(id:Int) {

        listaDeUsuarios.forEach {
            usuario ->
            if(usuario.idUsuario == id){
                listaDeUsuarios.remove(usuario)
            }
        }
    }

    fun agregarUsuario(id : Int, nombre : String, password : String, tipoDeUsuario: TipoUsuario) {

        listaDeUsuarios.forEach {
            usuario ->
            if(usuario.idUsuario != id){

                listaDeUsuarios.add(Usuario(id,nombre,password,tipoDeUsuario))
            }
        }
    }


}