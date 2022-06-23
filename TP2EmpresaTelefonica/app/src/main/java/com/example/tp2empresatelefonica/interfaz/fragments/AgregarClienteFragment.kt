package com.example.tp2empresatelefonica.interfaz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.navigation.findNavController
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.databinding.FragmentAgregarClienteBinding


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
            navController.navigate(R.id.action_agregarClienteFragment_to_menuAdmin)
        }
    }

    private fun agregrarCliente() {


    }

}