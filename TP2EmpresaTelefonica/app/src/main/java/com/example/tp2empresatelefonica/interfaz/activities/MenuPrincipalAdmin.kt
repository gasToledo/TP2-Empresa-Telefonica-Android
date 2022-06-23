package com.example.tp2empresatelefonica.interfaz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
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
import java.time.LocalDate

class MenuPrincipalAdmin : AppCompatActivity() {

    private lateinit var binding: MenuPrincipalAdminBinding
    private val sistemaPrincipal = Sistema()
    private lateinit var clienteSeleccionado : Cliente
    private var listaDeClientes  = mutableListOf<Cliente>()

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

                /*
                Abrir un fragmento que permita agregar el cliente
                ingresando informacion
                 */
                iniciarFragmentoAgregarCliente()

                true

            }

            R.id.action_quitar_cliente -> {

                /*
                Agregar un fragmento que permita quitar al cliente
                ingresando su nombre o ID
                 */
                iniciarFragmentoQuitarCliente()
                true
            }

            R.id.action_salir_cuenta -> {

                val intent = Intent(this, InicioDeSesion::class.java)
                startActivity(intent)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    private fun iniciarFragmentoAgregarCliente() {

        val navController = Navigation.findNavController(binding.navHostFragmentContainer)

        navController.navigate(R.id.action_menuAdmin_to_agregarClienteFragment)
    }


    private fun iniciarFragmentoQuitarCliente(){

        val navController = Navigation.findNavController(binding.navHostFragmentContainer)

        navController.navigate(R.id.action_menuAdmin_to_quitarClienteFragment)
    }


    private fun iniciarMenu(){

       /* val intent : Intent = intent
        val userName = intent.getStringExtra("nombre_del_usuario")

        binding.tituloMenuPrincipalAdmin.text = "Bienvenido al menu $userName"*/
        iniciarSistema(sistemaPrincipal)

    }


    private fun iniciarSistema(sistema : Sistema){

        var codigoCliente = 1

        repeat(15){
            sistema.darDeAltaCliente(codigoCliente,"Cliente $codigoCliente","", LocalDate.now())
            codigoCliente++
        }

        sistema.darDeAltaCliente(340,"Cliente $codigoCliente","", LocalDate.of(2019,6,6))
        sistema.darDeAltaCliente(12023,"Cliente ${codigoCliente + 1}","", LocalDate.of(2020,11,6))

        listaDeClientes = sistema.obtenerListaDeClientes()

        listaDeClientes.forEach { cliente ->

            if(cliente.codigoDeCliente() % 2 == 0) {
                repeat(10) {
                    sistema.realizarLlamada(cliente, "06-06-2020", "22:00", 550.0, 'I')
                }
            }else {
                repeat(10){
                    sistema.realizarLlamada(cliente, "10-09-2020", "12:00", 122.0, 'L')
                }
            }
        }


    }


}