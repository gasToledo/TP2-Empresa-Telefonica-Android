package com.example.tp2empresatelefonica.interfaz.fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp2empresatelefonica.clases.sistema.Sistema
import com.example.tp2empresatelefonica.databinding.FragmentMenuClienteBinding
import com.example.tp2empresatelefonica.interfaz.adapters.AdapterListaDeLlamadasPorCliente
import com.example.tp2empresatelefonica.repositorios.ClientesRepository


class MenuCliente : Fragment() {

    private lateinit var binding: FragmentMenuClienteBinding
    private val sistemaPrincipal = Sistema()
    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuClienteBinding.inflate(inflater, container, false)

        iniciarMenu()
        return binding.root
    }

    private fun iniciarMenu() {

        preferences =
            this.requireActivity().getSharedPreferences("CLIENTE_ID", Context.MODE_PRIVATE)
        val datosCliente = preferences.getInt("CLIENTE_ID", 0)


        binding.tituloMenuPrincipalCliente.text = "Bienvenido ${
            ClientesRepository.obtenerCliente(datosCliente)
                ?.nombreDeCliente()
        }"

        iniciarSistema(sistemaPrincipal)
        iniciarRecyclerView(datosCliente)

    }

    private fun iniciarRecyclerView(id: Int) {
        binding.rvRegistroDeLlamadaPorCliente.layoutManager =
            LinearLayoutManager(binding.root.context)
        binding.rvRegistroDeLlamadaPorCliente.setHasFixedSize(true)

        if (id > 0) {
            val customAdapter = sistemaPrincipal.obtenerListaDeLlamadasPorCliente(id)
                ?.let { AdapterListaDeLlamadasPorCliente(it) }
            binding.rvRegistroDeLlamadaPorCliente.adapter = customAdapter
        } else {
            val customAdapter = sistemaPrincipal.obtenerListaDeLlamadasPorCliente(1)
                ?.let { AdapterListaDeLlamadasPorCliente(it) }
            binding.rvRegistroDeLlamadaPorCliente.adapter = customAdapter
        }


    }


    private fun iniciarSistema(sistema: Sistema) {

        sistema.iniciarClientesPredeterminados()

    }

}