package com.example.tp2empresatelefonica.interfaz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.clases.usuarios.TipoUsuario


class MenuInicial : Fragment() {


    private lateinit var message : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        message = arguments!!.getString("tipoDeUsuario").toString()

        return inflater.inflate(R.layout.fragment_menu_inicial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        comenzarFragmento(message,view)

    }

    private fun comenzarFragmento(tipoDeUsuario: String, view: View) {

        if (tipoDeUsuario == TipoUsuario.CLIENTE.name){

            iniciarMenuCliente(view)
        }
        else {
            iniciarMenuAdmin(view)
        }
    }

    private fun iniciarMenuCliente(view : View){

        val navController = view.findNavController()
        navController.navigate(R.id.action_fragment_menu_inicial_to_menuCliente)
    }

    private fun iniciarMenuAdmin(view: View){
        val navController = view.findNavController()
        navController.navigate(R.id.action_fragment_menu_inicial_to_menuAdmin)
    }

}