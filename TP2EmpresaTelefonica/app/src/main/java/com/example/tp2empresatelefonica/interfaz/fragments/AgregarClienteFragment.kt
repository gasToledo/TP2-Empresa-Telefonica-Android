package com.example.tp2empresatelefonica.interfaz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.databinding.FragmentAgregarClienteBinding
import com.example.tp2empresatelefonica.repositorios.ClientesRepository


class AgregarClienteFragment : Fragment() {

    private lateinit var binding : FragmentAgregarClienteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       binding = FragmentAgregarClienteBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = view.findNavController()

        binding.agregarClienteButton.setOnClickListener {


            if (binding.agregarClienteId.text.isNullOrEmpty() || binding.agregarClienteNombre.text.isNullOrEmpty() || binding.agregarClienteApellido.text.isNullOrEmpty()) {

                Toast.makeText(binding.root.context, "Ingrese datos validos porfavor.", Toast.LENGTH_SHORT).show()
            }
            else if(!comprobarExistenciaCliente(binding.agregarClienteId.text.toString().toInt())) {

                ClientesRepository.agregarCliente(id,binding.agregarClienteNombre.text.toString(),binding.agregarClienteApellido.text.toString())
                Toast.makeText(binding.root.context,"Agregado con exito",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(binding.root.context,"El cliente ya se encuentra registrado.",Toast.LENGTH_SHORT).show()

            }



        }

        binding.agregarClienteButtonSalir.setOnClickListener {
            navController.navigate(R.id.action_agregarClienteFragment_to_menuAdmin)

        }

    }

    private fun comprobarExistenciaCliente(clienteId : Int): Boolean {

        return ClientesRepository.obtenerCliente(clienteId) != null
    }




}