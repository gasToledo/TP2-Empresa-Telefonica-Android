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
import com.example.tp2empresatelefonica.databinding.FragmentMenuClienteBinding
import com.example.tp2empresatelefonica.interfaz.adapters.AdapterListaDeLlamadasPorCliente
import java.time.LocalDate


class MenuCliente : Fragment() {

    private lateinit var binding : FragmentMenuClienteBinding
    private val sistemaPrincipal = Sistema()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMenuClienteBinding.inflate(inflater,container,false)

        iniciarMenu()
        return binding.root
    }

    private fun iniciarMenu(){

        /*val intent : Intent = intent
        val userName = intent.getStringExtra("nombre_del_usuario")
        binding.tituloMenuPrincipalCliente.text = "Bienvenido al menu $userName"*/

        iniciarSistema(sistemaPrincipal)
        iniciarRecyclerView()

    }

    private fun iniciarRecyclerView(){
        binding.rvRegistroDeLlamadaPorCliente.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvRegistroDeLlamadaPorCliente.setHasFixedSize(true)
        val customAdapter = sistemaPrincipal.obtenerListaDeLlamadasPorCliente(Cliente(1))
            ?.let { AdapterListaDeLlamadasPorCliente(it) }
        binding.rvRegistroDeLlamadaPorCliente.adapter = customAdapter

    }

    private fun iniciarSistema(sistema: Sistema) {

        sistema.darDeAltaCliente(1,"cliente 1", "", LocalDate.now())

        val listaDeClientes = sistema.obtenerListaDeClientes()
        var contador = 1

        listaDeClientes.forEach { cliente ->

            repeat(20){
                if(contador % 2 == 0) {
                    sistema.realizarLlamada(cliente, "06-06-2020", "22:00:00", 550.0, 'I')

                }else {
                    sistema.realizarLlamada(cliente, "09-10-2020", "12:00:00", 122.0, 'L')
                }
                contador++
            }

        }


    }

}