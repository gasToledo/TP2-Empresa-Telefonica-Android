package com.example.tp2empresatelefonica.interfaz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.clases.cliente.Cliente
import com.example.tp2empresatelefonica.databinding.FragmentQuitarClienteBinding

class QuitarClienteFragment : Fragment() {

    private lateinit var binding : FragmentQuitarClienteBinding
    private var listaDeClientes = mutableListOf<Cliente>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentQuitarClienteBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = view.findNavController()

        binding.quitarClienteButton.setOnClickListener {

            if(quitarCliente(binding.quitarClienteId.text.toString(), binding.quitarClienteNombre.text.toString())){
                navController.navigate(R.id.action_quitarClienteFragment_to_menuAdmin)
            }
            else if (binding.quitarClienteId.text.isNullOrEmpty() || binding.quitarClienteNombre.text.isNullOrEmpty()) {

                Toast.makeText(binding.root.context, "Ingrese datos validos porfavor.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.quitarClienteButtonSalir.setOnClickListener {
            navController.navigate(R.id.action_quitarClienteFragment_to_menuAdmin)
        }
    }

    private fun quitarCliente(id : String, nombre : String) : Boolean{

        listaDeClientes.forEach { cliente ->

            if(cliente.codigoDeCliente().toString() != id && cliente.nombreDeCliente() != nombre){

                listaDeClientes.remove(cliente)
                Toast.makeText(binding.root.context, "$nombre a sido removido con exito.", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        Toast.makeText(binding.root.context, "Cliente no existente", Toast.LENGTH_SHORT).show()
        return false
    }


}