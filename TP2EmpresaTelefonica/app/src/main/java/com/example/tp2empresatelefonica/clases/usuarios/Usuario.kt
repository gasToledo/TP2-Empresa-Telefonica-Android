package com.example.tp2empresatelefonica.clases.usuarios

class Usuario(
    val idUsuario: Int,
    val user: String,
    val password: String,
    var tipoUsuario: TipoUsuario = TipoUsuario.CLIENTE
) {


    fun convertirEnAdmin() {
        this.tipoUsuario = TipoUsuario.ADMINISTRADOR
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (idUsuario != other.idUsuario) return false

        return true
    }

    override fun hashCode(): Int {
        return idUsuario
    }

}