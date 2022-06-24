package com.example.tp2empresatelefonica.interfaz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.navigation.findNavController
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.clases.cliente.Cliente
import com.example.tp2empresatelefonica.databinding.FragmentAgregarClienteBinding


class AgregarClienteFragment : Fragment() {

    private lateinit var binding : FragmentAgregarClienteBinding
    private var listaDeClientesRegistrados = mutableListOf<Cliente>(
        Cliente(2,"G","T"),
        Cliente(100,"M","T")
    )


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

            if(agregrarCliente(binding.agregarClienteId.toString())){

                navController.navigate(R.id.action_agregarClienteFragment_to_menuAdmin)
            }
            else if (binding.agregarClienteId.text.isNullOrEmpty() || binding.agregarClienteNombre.text.isNullOrEmpty() || binding.agregarClienteApellido.text.isNullOrEmpty()) {

                Toast.makeText(binding.root.context, "Ingrese datos validos porfavor.", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(binding.root.context, "Cliente ya registrado", Toast.LENGTH_SHORT).show()
            }
        }

        binding.agregarClienteButtonSalir.setOnClickListener {
            navController.navigate(R.id.action_agregarClienteFragment_to_menuAdmin)
        }
    }

    private fun agregrarCliente(id: String) : Boolean {

        listaDeClientesRegistrados.forEach { cliente ->

            if(cliente.codigoDeCliente().toString() != id){

                listaDeClientesRegistrados.add(
                    Cliente(
                        id.toInt(),
                        binding.agregarClienteNombre.text.toString(),
                        binding.agregarClienteApellido.text.toString()
                    )
                )
                Toast.makeText(binding.root.context, "${binding.agregarClienteNombre.text}, ${binding.agregarClienteApellido.text} a sido añadido con exito.", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return false
    }

}