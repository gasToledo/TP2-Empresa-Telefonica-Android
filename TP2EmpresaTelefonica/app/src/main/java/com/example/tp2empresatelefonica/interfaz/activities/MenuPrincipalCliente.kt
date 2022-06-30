package com.example.tp2empresatelefonica.interfaz.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

    private lateinit var preferences: SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

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



        inicializarElementos()

        guardarDatosCliente(informacionLlego!!.getInt(claveMensaje))

        println("Datos del cliente: ${obtenerDatosCliente()}")
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


        val navController = Navigation.findNavController(binding.navHostFragmentContainerCliente)

        when(navController.currentDestination?.label){

            labelMenuCliente -> navController.navigate(R.id.action_menuCliente_to_realizarLlamadaFragment)

            else -> Toast.makeText(this,"No es posible", Toast.LENGTH_SHORT).show()

        }



    }

    private fun iniciarFragmentoClienteHome() {


        val navController = Navigation.findNavController(binding.navHostFragmentContainerCliente)

        when(navController.currentDestination?.label){

            labelRealizarLlamadaFragment -> navController.navigate(R.id.action_realizarLlamadaFragment_to_menuCliente)

            else -> Toast.makeText(this,"No es posible", Toast.LENGTH_SHORT).show()

        }


    }

    private fun volverAInicioSesion(){

        val intent = Intent(this, InicioDeSesion::class.java)
        startActivity(intent)
    }

    private fun inicializarElementos(){

        preferences = this.getSharedPreferences("CLIENTE_ID", Context.MODE_PRIVATE)
        editor = preferences.edit()
    }

    private fun guardarDatosCliente(id: Int) {

        editor.putInt("CLIENTE_ID", id)
        editor.apply()
    }

    private fun obtenerDatosCliente(): Int {

        return preferences.getInt("CLIENTE_ID", 0)
    }
}



