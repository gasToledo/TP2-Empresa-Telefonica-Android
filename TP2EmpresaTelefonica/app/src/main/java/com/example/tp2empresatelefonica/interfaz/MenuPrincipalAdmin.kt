package com.example.tp2empresatelefonica.interfaz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.clases.sistema.Sistema
import com.example.tp2empresatelefonica.databinding.MenuPrincipalAdminBinding
import java.time.LocalDate

class MenuPrincipalAdmin : AppCompatActivity() {

    private lateinit var binding: MenuPrincipalAdminBinding
    private val sistemaPrincipal = Sistema()

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
        val customAdapter = AdapterListaDeClientes(sistemaPrincipal.obtenerListaDeClientes(),sistemaPrincipal)
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
                    sistema.realizarLlamada(cliente, "2020-06-06", "22:00", 550.0, 'I')
                }
            }else {
                repeat(10){
                    sistema.realizarLlamada(cliente, "2020-06-06", "22:00", 550.0, 'L')
                }
            }
        }


        //sistema.realizarLlamada(sistema.obtenerCliente(1),"2020-06-06","20:00",200.0,'L')

    }


}