package com.example.tp2empresatelefonica.interfaz.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.clases.cliente.Cliente
import com.example.tp2empresatelefonica.clases.sistema.Sistema
import com.example.tp2empresatelefonica.databinding.FragmentMenuAdminBinding
import com.example.tp2empresatelefonica.databinding.MenuPrincipalAdminBinding
import com.example.tp2empresatelefonica.interfaz.adapters.AdapterListaDeClientes
import java.time.LocalDate


class MenuAdmin : Fragment() {

    private lateinit var binding: FragmentMenuAdminBinding
    private val sistemaPrincipal = Sistema()
    private lateinit var clienteSeleccionado : Cliente

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMenuAdminBinding.inflate(inflater, container, false)

        iniciarMenu()
        return binding.root
    }

    private fun iniciarMenu(){

        /*val intent : Intent = intent
        val userName = intent.getStringExtra("nombre_del_usuario")

        binding.tituloMenuPrincipalAdmin.text = "Bienvenido al menu $userName"*/
        iniciarSistema(sistemaPrincipal)

        iniciarRecyclerView()

    }

    private fun iniciarRecyclerView(){

        binding.rvRegistroDeLlamadas.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvRegistroDeLlamadas.setHasFixedSize(true)
        val customAdapter = AdapterListaDeClientes(sistemaPrincipal.obtenerListaDeClientes(),sistemaPrincipal){ clienteSeleccionado = it }
        binding.rvRegistroDeLlamadas.adapter = customAdapter
    }

    private fun iniciarSistema(sistema : Sistema){

        var codigoCliente = 1

        repeat(15){
            sistema.darDeAltaCliente(codigoCliente,"Cliente $codigoCliente","", LocalDate.now())
            codigoCliente++
        }

        sistema.darDeAltaCliente(340,"Cliente $codigoCliente","", LocalDate.of(2019,6,6))
        sistema.darDeAltaCliente(12023,"Cliente ${codigoCliente + 1}","", LocalDate.of(2020,11,6))

        val listaDeClientes = sistema.obtenerListaDeClientes()

        listaDeClientes.forEach { cliente ->

            if(cliente.codigoDeCliente() % 2 == 0) {
                repeat(10) {
                    sistema.realizarLlamada(cliente, "06-06-2020", "22:00", 550.0, 'I')
                }
            }else {
                repeat(10){
                    sistema.realizarLlamada(cliente, "10-09-2020", "12:00", 122.0, 'L')
                }
            }
        }


    }

}