package com.example.tp2empresatelefonica.interfaz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.clases.usuarios.TipoUsuario
import com.example.tp2empresatelefonica.databinding.MenuPrincipalBinding
import com.example.tp2empresatelefonica.interfaz.fragments.MenuInicial

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: MenuPrincipalBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MenuPrincipalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val intent : Intent = intent
        val tipoDeUsuario = intent.getStringExtra("tipoDeUsuario").toString()

    }




}