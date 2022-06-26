package com.example.tp2empresatelefonica.interfaz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.clases.cliente.Cliente
import com.example.tp2empresatelefonica.clases.sistema.Sistema
import com.example.tp2empresatelefonica.databinding.MenuPrincipalAdminBinding
import com.example.tp2empresatelefonica.interfaz.adapters.AdapterListaDeClientes
import com.example.tp2empresatelefonica.interfaz.fragments.MenuAdmin
import com.example.tp2empresatelefonica.repositorios.ClientesRepository
import com.example.tp2empresatelefonica.repositorios.LlamadasRepository
import java.time.LocalDate

class MenuPrincipalAdmin : AppCompatActivity() {

    private lateinit var binding: MenuPrincipalAdminBinding
    private val sistemaPrincipal = Sistema()

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

    private fun iniciarFragmentoAgregarCliente() {


        val navController = Navigation.findNavController(binding.navHostFragmentContainerAdmin)
        navController.navigate(R.id.action_menuAdmin_to_agregarClienteFragment)
    }


    private fun iniciarFragmentoQuitarCliente(){

        val navController = Navigation.findNavController(binding.navHostFragmentContainerAdmin)

        navController.navigate(R.id.action_menuAdmin_to_quitarClienteFragment)
    }


    private fun iniciarMenu(){

        iniciarSistema(sistemaPrincipal)

    }


    private fun iniciarSistema(sistema : Sistema){

        sistema.iniciarClientesPredeterminados()

    }


}