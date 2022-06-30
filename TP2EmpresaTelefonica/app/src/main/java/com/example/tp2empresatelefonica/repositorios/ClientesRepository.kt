package com.example.tp2empresatelefonica.repositorios

import com.example.tp2empresatelefonica.clases.cliente.Cliente

import java.time.LocalDate

object ClientesRepository {

    private val listaDeClientes = mutableListOf<Cliente>()


    init {

        listaDeClientes.addAll(

            listOf(
                Cliente(1, "Cliente 1", fecha_de_alta_cliente = LocalDate.of(2019, 6, 6)),
                Cliente(2, "Cliente 2"),
                Cliente(3, "Cliente 3", fecha_de_alta_cliente = LocalDate.of(2022, 5, 5)),
                Cliente(4, "Cliente 4", fecha_de_alta_cliente = LocalDate.of(2019, 6, 9)),
                Cliente(5, "Cliente 5"),
                Cliente(6, "Cliente 6", fecha_de_alta_cliente = LocalDate.of(2018, 5, 10)),
                Cliente(7, "Cliente 7", fecha_de_alta_cliente = LocalDate.of(2019, 6, 23)),
                Cliente(123, "Cliente 8"),
                Cliente(234, "Cliente 9", fecha_de_alta_cliente = LocalDate.of(2017, 5, 1)),
                Cliente(8, "Cliente 10", fecha_de_alta_cliente = LocalDate.of(2020, 6, 30)),
                Cliente(9, "Cliente 11"),
                Cliente(900, "Cliente 12", fecha_de_alta_cliente = LocalDate.of(2022, 1, 2)),
                Cliente(750, "Cliente 13", fecha_de_alta_cliente = LocalDate.of(2019, 3, 17)),
                Cliente(10, "Cliente 14"),
                Cliente(11, "Cliente 15", fecha_de_alta_cliente = LocalDate.of(2022, 6, 2)),
            )
        )
    }

    fun obtenerCliente(id: Int): Cliente? {

        listaDeClientes.forEach { cliente ->

            if (cliente.codigoDeCliente() == id) {

                return cliente
            }
        }
        return null
    }

    fun obtenerListaDeClientes(): MutableList<Cliente> {

        return listaDeClientes
    }

    fun agregarCliente(id: Int, nombre: String, apellido: String) {

        listaDeClientes.add(Cliente(id, nombre, apellido))
    }

    fun removerClientes(id: Int) {

        listaDeClientes.remove(obtenerCliente(id))
    }


}