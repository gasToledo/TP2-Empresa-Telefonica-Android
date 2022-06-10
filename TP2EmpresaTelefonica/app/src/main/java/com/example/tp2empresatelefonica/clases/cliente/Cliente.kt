package com.example.tp2empresatelefonica.clases.cliente

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class Cliente (
    private val codigo_cliente : Int,
    private val nombre_cliente : String = "",
    private val apellido_cliente:String = "",
    private val fecha_de_alta_cliente : LocalDate = LocalDate.now(),
    private var tipoCliente : TipoCliente = TipoCliente.NUEVO
) {

    init {
        tipoDeCliente()
    }

    private fun tipoDeCliente(){
        val haceSeisMeses = LocalDate.now().minusMonths(6)

        this.tipoCliente =
            if(fecha_de_alta_cliente in haceSeisMeses..LocalDate.now())
                TipoCliente.NUEVO
        else{
            TipoCliente.ESTANDAR
            }
    }

    fun codigoDeCliente() : Int = this.codigo_cliente

    fun tipoCliente() : TipoCliente = this.tipoCliente

    fun nombreDeCliente() : String = this.nombre_cliente

    fun apellidoDeCliente() : String = this.apellido_cliente

    override fun toString(): String {
        return """
        
        Codigo: $codigo_cliente
        Nombre: $nombre_cliente
        Apellido: $apellido_cliente
        Tipo de cliente: $tipoCliente
        Fecha de alta: $fecha_de_alta_cliente
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cliente

        return (codigo_cliente == other.codigo_cliente)
    }

    override fun hashCode(): Int {
        return codigo_cliente
    }
}