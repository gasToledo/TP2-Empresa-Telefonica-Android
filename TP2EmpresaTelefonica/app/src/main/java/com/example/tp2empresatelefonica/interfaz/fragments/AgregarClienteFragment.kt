package com.example.tp2empresatelefonica.interfaz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tp2empresatelefonica.clases.sistema.Sistema
import com.example.tp2empresatelefonica.clases.usuarios.TipoUsuario
import com.example.tp2empresatelefonica.databinding.FragmentAgregarClienteBinding
import com.example.tp2empresatelefonica.repositorios.ClientesRepository
import com.example.tp2empresatelefonica.repositorios.UsersRepository
import java.time.LocalDate


class AgregarClienteFragment : Fragment() {

    private lateinit var binding: FragmentAgregarClienteBinding
    private val sistema = Sistema()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAgregarClienteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.agregarClienteButton.setOnClickListener {


            if (binding.agregarClienteId.text.isNullOrEmpty() || binding.agregarClienteNombre.text.isNullOrEmpty() || binding.agregarClienteApellido.text.isNullOrEmpty()) {

                Toast.makeText(
                    binding.root.context,
                    "Ingrese datos validos porfavor.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!comprobarExistenciaCliente(
                    binding.agregarClienteId.text.toString().toInt()
                )
            ) {

                sistema.darDeAltaCliente(
                    binding.agregarClienteId.text.toString().toInt(),
                    binding.agregarClienteNombre.text.toString(),
                    binding.agregarClienteApellido.text.toString(),
                    LocalDate.now()
                )
                UsersRepository.agregarUsuario(
                    binding.agregarClienteId.text.toString().toInt(),
                    binding.agregarClienteNombre.text.toString(),
                    binding.agregarClienteNombre.text.toString(),
                    TipoUsuario.CLIENTE
                )

                Toast.makeText(binding.root.context, "Agregado con exito", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    binding.root.context,
                    "El cliente ya se encuentra registrado.",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }

    }

    private fun comprobarExistenciaCliente(clienteId: Int): Boolean {

        return ClientesRepository.obtenerCliente(clienteId) != null
    }


}