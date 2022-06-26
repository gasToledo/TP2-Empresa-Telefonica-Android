package com.example.tp2empresatelefonica.repositorios

import android.widget.Toast
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.clases.cliente.Cliente
import com.example.tp2empresatelefonica.clases.llamada.Llamada
import com.example.tp2empresatelefonica.excepciones.ClienteExistente
import com.example.tp2empresatelefonica.excepciones.NoExisteCliente
import java.time.LocalDate

object ClientesRepository {

    private val listaDeClientes = mutableListOf<Cliente>()


    init {

        listaDeClientes.addAll(

            listOf(
                Cliente(1,"Cliente 1", fecha_de_alta_cliente = LocalDate.of(2019,6,6)),
                Cliente(2,"Cliente 2"),
                Cliente(3,"Cliente 3", fecha_de_alta_cliente = LocalDate.of(2022,5,5)),
                Cliente(4,"Cliente 4", fecha_de_alta_cliente = LocalDate.of(2019,6,9)),
                Cliente(5,"Cliente 5"),
                Cliente(6,"Cliente 6", fecha_de_alta_cliente = LocalDate.of(2018,5,10)),
                Cliente(7,"Cliente 7", fecha_de_alta_cliente = LocalDate.of(2019,6,23)),
                Cliente(123,"Cliente 8"),
                Cliente(234,"Cliente 9", fecha_de_alta_cliente = LocalDate.of(2017,5,1)),
                Cliente(8,"Cliente 10", fecha_de_alta_cliente = LocalDate.of(2020,6,30)),
                Cliente(9,"Cliente 11"),
                Cliente(900,"Cliente 12", fecha_de_alta_cliente = LocalDate.of(2022,1,2)),
                Cliente(750,"Cliente 13", fecha_de_alta_cliente = LocalDate.of(2019,3,17)),
                Cliente(10,"Cliente 14"),
                Cliente(11,"Cliente 15", fecha_de_alta_cliente = LocalDate.of(2022,6,2)),
                )
            )
    }

    fun obtenerCliente(id : Int) : Cliente {

        listaDeClientes.forEach {
            cliente ->

            if(cliente.codigoDeCliente() == id){

                return cliente
            }
        }
        throw NoExisteCliente("[ERROR] No existe el cliente.")
    }

    fun obtenerListaDeClientes() : MutableList<Cliente> {

        return listaDeClientes
    }

    fun agregarClientes(id: Int, nombre : String, apellido : String) : Boolean {

        listaDeClientes.forEach {
            cliente ->
            if(cliente.codigoDeCliente() != id){
                listaDeClientes.add(Cliente(id,nombre,apellido))
                return true
            }
        }
        throw ClienteExistente("[ERROR] Ya existe este cliente.")
    }

    fun removerClientes(id: Int) : Boolean{

        listaDeClientes.forEach {
            cliente ->
            if(cliente.codigoDeCliente() == id){
                listaDeClientes.remove(cliente)
                return true
            }
        }

        throw NoExisteCliente("[ERROR] Cliente no existente")
    }


}