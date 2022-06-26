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
import com.example.tp2empresatelefonica.repositorios.ClientesRepository
import com.example.tp2empresatelefonica.repositorios.LlamadasRepository
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


        binding.tituloMenuPrincipalCliente.text = "Bienvenido al menu ${ClientesRepository.obtenerCliente(1).nombreDeCliente()}"

        iniciarSistema(sistemaPrincipal)
        iniciarRecyclerView()

    }

    private fun iniciarRecyclerView(){
        binding.rvRegistroDeLlamadaPorCliente.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvRegistroDeLlamadaPorCliente.setHasFixedSize(true)
        val customAdapter = sistemaPrincipal.obtenerListaDeLlamadasPorCliente(1)
            ?.let { AdapterListaDeLlamadasPorCliente(it) }
        binding.rvRegistroDeLlamadaPorCliente.adapter = customAdapter

    }



    private fun iniciarSistema(sistema: Sistema) {

        sistema.iniciarClientesPredeterminados()

    }

}