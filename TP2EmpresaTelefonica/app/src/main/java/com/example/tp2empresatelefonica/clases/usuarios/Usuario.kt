package com.example.tp2empresatelefonica.clases.usuarios

class Usuario(val user : String, val password : String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (user != other.user) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}