package com.example.tp2empresatelefonica.interfaz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tp2empresatelefonica.databinding.FragmentQuitarClienteBinding
import com.example.tp2empresatelefonica.repositorios.ClientesRepository
import com.example.tp2empresatelefonica.repositorios.UsersRepository

class QuitarClienteFragment : Fragment() {

    private lateinit var binding: FragmentQuitarClienteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentQuitarClienteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.quitarClienteButton.setOnClickListener {


            if (binding.quitarClienteId.text.isNullOrEmpty() || binding.quitarClienteNombre.text.isNullOrEmpty()) {

                Toast.makeText(
                    binding.root.context,
                    "Ingrese datos validos porfavor.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (comprobarExistenciaCliente(
                    binding.quitarClienteId.text.toString().toInt()
                )
            ) {

                ClientesRepository.removerClientes(binding.quitarClienteId.text.toString().toInt())
                UsersRepository.removerUsuario(binding.quitarClienteId.text.toString().toInt())

                Toast.makeText(
                    binding.root.context,
                    "El cliente ha sido eliminado.",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                Toast.makeText(
                    binding.root.context,
                    "Cliente no se encuentra registrado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun comprobarExistenciaCliente(clienteId: Int): Boolean {

        return ClientesRepository.obtenerCliente(clienteId) != null
    }
}