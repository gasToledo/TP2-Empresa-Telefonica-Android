package com.example.tp2empresatelefonica.interfaz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp2empresatelefonica.clases.cliente.Cliente
import com.example.tp2empresatelefonica.clases.sistema.Sistema
import com.example.tp2empresatelefonica.databinding.FragmentMenuAdminBinding
import com.example.tp2empresatelefonica.interfaz.adapters.AdapterListaDeClientes


class MenuAdmin : Fragment() {

    private lateinit var binding: FragmentMenuAdminBinding
    private val sistemaPrincipal = Sistema()
    private lateinit var clienteSeleccionado: Cliente

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuAdminBinding.inflate(inflater, container, false)

        iniciarMenu()
        return binding.root
    }

    private fun iniciarMenu() {

        iniciarSistema(sistemaPrincipal)

        iniciarRecyclerView()
    }

    private fun iniciarRecyclerView() {

        binding.rvRegistroDeLlamadas.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvRegistroDeLlamadas.setHasFixedSize(true)
        val customAdapter = AdapterListaDeClientes(
            sistemaPrincipal.obtenerListaDeClientes(),
            sistemaPrincipal
        ) { clienteSeleccionado = it }
        binding.rvRegistroDeLlamadas.adapter = customAdapter
    }

    private fun iniciarSistema(sistema: Sistema) {

        sistema.iniciarClientesPredeterminados()
    }

}