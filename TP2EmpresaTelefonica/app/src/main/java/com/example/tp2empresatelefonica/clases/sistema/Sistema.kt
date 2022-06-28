package com.example.tp2empresatelefonica.clases.sistema

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.tp2empresatelefonica.clases.cliente.Cliente
import com.example.tp2empresatelefonica.clases.cliente.TipoCliente
import com.example.tp2empresatelefonica.clases.llamada.Llamada
import com.example.tp2empresatelefonica.clases.llamada.LlamadaFinDeSemana
import com.example.tp2empresatelefonica.clases.llamada.LlamadaNocturna
import com.example.tp2empresatelefonica.clases.llamada.LlamadaRegular
import com.example.tp2empresatelefonica.excepciones.NoExisteCliente
import com.example.tp2empresatelefonica.excepciones.TipoDeLlamadaErroneo
import com.example.tp2empresatelefonica.repositorios.ClientesRepository
import com.example.tp2empresatelefonica.repositorios.LlamadasRepository
import java.math.RoundingMode
import java.time.DateTimeException
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


@RequiresApi(Build.VERSION_CODES.O)
class Sistema {

    private val listaClientes = mutableMapOf<Cliente,MutableList<Llamada>>()


    private fun ingresarClientes(alta : Cliente) {
        val historialLlamadas = mutableListOf<Llamada>()

        if(ClientesRepository.obtenerCliente(alta.codigoDeCliente()) == null) {
            ClientesRepository.agregarCliente(
                alta.codigoDeCliente(),
                alta.nombreDeCliente(),
                alta.apellidoDeCliente()
            )
        }

        listaClientes[alta] = historialLlamadas
    }

    fun iniciarClientesPredeterminados() {

        ClientesRepository.obtenerListaDeClientes().forEach { cliente ->

            ingresarClientes(cliente)

            LlamadasRepository.obtenerListaDeLlamadas().forEach { llamada ->

                if (llamada.codigo_cliente == cliente.codigoDeCliente()) {

                    listaClientes[cliente]?.add(llamada)
                }
            }
        }
    }

    private fun verificarCliente(cliente: Cliente){
        if(!listaClientes.contains(cliente)){
            throw NoExisteCliente("[ERROR] Cliente no existente.")
        }
    }

    private fun verificarCliente(codigo: Int){
        if(!listaClientes.contains(Cliente(codigo))){
            throw NoExisteCliente("[ERROR] Cliente no existente.")
        }
    }

    fun obtenerListaDeClientes() : MutableList<Cliente> {

        return listaClientes.keys.toMutableList()
    }

    fun obtenerListaDeLlamadasPorCliente(id: Int) : MutableList<Llamada>? {

        verificarCliente(id)

        return listaClientes[Cliente(id)]?.toMutableList()

    }


    private fun ingresarHora(hora : String): LocalTime {

        var time : LocalTime = LocalTime.now()
        try {
             time = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"))
        }catch (e:DateTimeException){
            println(e.message)
        }

        return time
    }

    private fun ingresarFecha(fecha : String) : LocalDate {

         var date : LocalDate = LocalDate.now()

        try {
            val dateAux = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            date = if (dateAux.isAfter(LocalDate.now())) LocalDate.now() else dateAux
        } catch (e : DateTimeParseException) {
            println(e.message)
        }

        return date
    }

    private fun ingresarTipoDeLlamada(tipo : Char) {

        if(!(tipo == 'L' || tipo == 'I')){

            throw TipoDeLlamadaErroneo("[ERROR] Tipo de llamada erroneo.")
        }
    }

    fun darDeAltaCliente(codigo : Int, nombre : String, apellido : String, date : LocalDate) {

        val clienteAlta = Cliente(codigo, nombre, apellido, date)

        ingresarClientes(clienteAlta)
    }

    fun obtenerCliente(codigo: Int) : Cliente {
        listaClientes.keys.forEach {
            if(it.codigoDeCliente() == codigo){
                return it
            }
        }
        throw NoExisteCliente("[ERROR] Cliente no existente.")
    }

    fun darDeBajaCliente(codigoBaja : Int) {

        verificarCliente(codigoBaja)

        listaClientes.remove(this.obtenerCliente(codigoBaja))

        if(ClientesRepository.obtenerCliente(codigoBaja) != null) {
            ClientesRepository.removerClientes(codigoBaja)
        }
    }

    private fun ingresarLlamadaACliente(cliente: Cliente, llamada : Llamada){

        verificarCliente(cliente)
        LlamadasRepository.agregarLlamada(llamada)
        //listaClientes[cliente]?.add(llamada)
    }

    fun realizarLlamada(cliente: Cliente, fecha: String, hora: String, duracion: Double, tipo: Char){

        verificarCliente(cliente)

        val fechaFinal = ingresarFecha(fecha)
        val horaFinal = ingresarHora(hora)

        ingresarTipoDeLlamada(tipo)

        val llamada : Llamada = if (esFinDeSemana(fechaFinal)) {
            LlamadaFinDeSemana(cliente.codigoDeCliente(),fechaFinal,horaFinal,duracion,tipo)

        } else if (esNoche(horaFinal) || cliente.tipoCliente() == TipoCliente.NUEVO) {
            LlamadaNocturna(cliente.codigoDeCliente(), fechaFinal,horaFinal,duracion,tipo)

        } else {
            LlamadaRegular(cliente.codigoDeCliente(),fechaFinal,horaFinal,duracion,tipo)
        }

        this.ingresarLlamadaACliente(cliente,llamada)
    }

    private fun esFinDeSemana(fecha : LocalDate) : Boolean {
        return fecha.dayOfWeek == DayOfWeek.SUNDAY || fecha.dayOfWeek == DayOfWeek.SATURDAY
    }

    private fun esNoche(hora : LocalTime) : Boolean {
        return hora.hour in 22 downTo 5
    }

    fun calcularCostoLlamadasCliente(codigo: Int) : Double {
        verificarCliente(codigo)

        var precioTotal = 0.0
        listaClientes[obtenerCliente(codigo)]?.forEach {
            precioTotal += it.calcularPrecio()
        }

        return precioTotal.toBigDecimal().setScale(2, RoundingMode.HALF_DOWN).toDouble()
    }

    fun calcularCostoLlamadaTodosClientes() : Double {

        var costoTotal = 0.0
        val lista = listaClientes.keys
        for (i in 0 until lista.size) {
            val codigo = lista.elementAt(i).codigoDeCliente()
            costoTotal += calcularCostoLlamadasCliente(codigo)
        }

        return costoTotal.toBigDecimal().setScale(2,RoundingMode.HALF_DOWN).toDouble()
    }
}



