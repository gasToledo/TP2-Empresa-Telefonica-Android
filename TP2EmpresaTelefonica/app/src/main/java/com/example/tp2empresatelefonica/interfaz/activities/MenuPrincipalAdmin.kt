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
import com.example.tp2empresatelefonica.clases.sistema.Sistema
import com.example.tp2empresatelefonica.databinding.MenuPrincipalAdminBinding

class MenuPrincipalAdmin : AppCompatActivity() {

    private lateinit var binding: MenuPrincipalAdminBinding
    private val sistemaPrincipal = Sistema()

    private val labelMenuAdmin = "fragment_menu_admin"
    private val labelAgregarClienteFragment = "fragment_agregar_cliente"
    private val labelQuitarClienteFragment = "fragment_quitar_cliente"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MenuPrincipalAdminBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarMenuAdmin)
        supportActionBar?.setDisplayShowTitleEnabled(false);
        iniciarMenu()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        MenuInflater(this).inflate(R.menu.toolbar_theme, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId){


            R.id.action_home_admin -> {

                volverAlMenu()

                true
            }

            R.id.action_agregar_cliente -> {

                iniciarFragmentoAgregarCliente()

                true
            }

            R.id.action_quitar_cliente -> {

                iniciarFragmentoQuitarCliente()

                true
            }

            R.id.action_salir_cuenta -> {

                val intent = Intent(this, InicioDeSesion::class.java)
                startActivity(intent)
                true
            }

            else -> {

                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                false
            }
        }

    private fun volverAlMenu() {

        val navController = Navigation.findNavController(binding.navHostFragmentContainerAdmin)

        when(navController.currentDestination?.label){

            labelAgregarClienteFragment -> navController.navigate(R.id.action_agregarClienteFragment_to_menuAdmin)

            labelQuitarClienteFragment -> navController.navigate(R.id.action_quitarClienteFragment_to_menuAdmin)

            else -> Toast.makeText(this,"No es posible", Toast.LENGTH_SHORT).show()
        }

    }

    private fun iniciarFragmentoAgregarCliente() {


        val navController = Navigation.findNavController(binding.navHostFragmentContainerAdmin)

        when(navController.currentDestination?.label){

            labelMenuAdmin -> navController.navigate(R.id.action_menuAdmin_to_agregarClienteFragment)

            labelQuitarClienteFragment -> navController.navigate(R.id.action_quitarClienteFragment_to_agregarClienteFragment)

            else -> Toast.makeText(this,"No es posible", Toast.LENGTH_SHORT).show()
        }

    }


    private fun iniciarFragmentoQuitarCliente(){

        val navController = Navigation.findNavController(binding.navHostFragmentContainerAdmin)

        when(navController.currentDestination?.label){

            labelMenuAdmin -> navController.navigate(R.id.action_menuAdmin_to_quitarClienteFragment)

            labelAgregarClienteFragment -> navController.navigate(R.id.action_agregarClienteFragment_to_quitarClienteFragment)

            else -> Toast.makeText(this,"No es posible", Toast.LENGTH_SHORT).show()
        }

    }


    private fun iniciarMenu(){

        iniciarSistema(sistemaPrincipal)

    }


    private fun iniciarSistema(sistema : Sistema){

        sistema.iniciarClientesPredeterminados()

    }


}