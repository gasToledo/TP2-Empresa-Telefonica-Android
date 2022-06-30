package com.example.tp2empresatelefonica.interfaz.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.databinding.MenuPrincipalClienteBinding


class MenuPrincipalCliente : AppCompatActivity() {

    private lateinit var binding: MenuPrincipalClienteBinding
    private val claveMensaje = "USER_ID"

    private val labelMenuCliente = "fragment_menu_cliente"
    private val labelRealizarLlamadaFragment = "RealizarLlamadaFragment"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MenuPrincipalClienteBinding.inflate(layoutInflater)
        val view = binding.root

        setSupportActionBar(binding.toolbarMenuCliente)
        supportActionBar?.setDisplayShowTitleEnabled(false);

        val intent = intent
        val informacionLlego : Bundle? = intent.extras



        setContentView(view)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        MenuInflater(this).inflate(R.menu.menu_drawer_cliente, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){

            R.id.nav_home_cliente -> {
                iniciarFragmentoClienteHome()
                true
            }

            R.id.nav_makecall_client -> {
                iniciarFragmentoDeLlamada()
                true
            }

            R.id.nav_logout_cliente -> {

                volverAInicioSesion()
                true
            }
        else -> {

            Toast.makeText(this, item.title,Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun iniciarFragmentoDeLlamada(){


        val intent = intent
        val informacionLlego : Bundle? = intent.extras

        val navController = Navigation.findNavController(binding.navHostFragmentContainerCliente)

        when(navController.currentDestination?.label){

            labelMenuCliente -> navController.navigate(R.id.action_menuCliente_to_realizarLlamadaFragment, informacionLlego)

            else -> Toast.makeText(this,"No es posible", Toast.LENGTH_SHORT).show()

        }



    }

    private fun iniciarFragmentoClienteHome() {

        val intent = intent
        val informacionLlego : Bundle? = intent.extras

        val navController = Navigation.findNavController(binding.navHostFragmentContainerCliente)

        when(navController.currentDestination?.label){

            labelRealizarLlamadaFragment -> navController.navigate(R.id.action_realizarLlamadaFragment_to_menuCliente, informacionLlego)

            else -> Toast.makeText(this,"No es posible", Toast.LENGTH_SHORT).show()

        }


    }

    private fun volverAInicioSesion(){

        val intent = Intent(this, InicioDeSesion::class.java)
        startActivity(intent)
    }
}



