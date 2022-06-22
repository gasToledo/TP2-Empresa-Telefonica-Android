package com.example.tp2empresatelefonica.interfaz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp2empresatelefonica.clases.cliente.Cliente
import com.example.tp2empresatelefonica.clases.sistema.Sistema
import com.example.tp2empresatelefonica.databinding.MenuPrincipalAdminBinding
import com.example.tp2empresatelefonica.interfaz.adapters.AdapterListaDeClientes
import java.time.LocalDate

class MenuPrincipalAdmin : AppCompatActivity() {

    private lateinit var binding: MenuPrincipalAdminBinding
    private val sistemaPrincipal = Sistema()
    private lateinit var clienteSeleccionado : Cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MenuPrincipalAdminBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        iniciarMenu()


    }

    private fun iniciarMenu(){

        val intent : Intent = intent
        val userName = intent.getStringExtra("nombre_del_usuario")

        binding.tituloMenuPrincipalAdmin.text = "Bienvenido al menu $userName"
        iniciarSistema(sistemaPrincipal)
        iniciarRecyclerView()

    }

    private fun iniciarRecyclerView(){

        binding.rvRegistroDeLlamadas.layoutManager = LinearLayoutManager(this)
        binding.rvRegistroDeLlamadas.setHasFixedSize(true)
        val customAdapter = AdapterListaDeClientes(sistemaPrincipal.obtenerListaDeClientes(),sistemaPrincipal){ clienteSeleccionado = it }
        binding.rvRegistroDeLlamadas.adapter = customAdapter
    }

    private fun iniciarSistema(sistema : Sistema){

        var codigoCliente = 1

        repeat(15){
            sistema.darDeAltaCliente(codigoCliente,"Cliente $codigoCliente","", LocalDate.now())
            codigoCliente++
        }

        sistema.darDeAltaCliente(340,"Cliente $codigoCliente","", LocalDate.of(2019,6,6))
        sistema.darDeAltaCliente(12023,"Cliente ${codigoCliente + 1}","", LocalDate.of(2020,11,6))

        val listaDeClientes = sistema.obtenerListaDeClientes()

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