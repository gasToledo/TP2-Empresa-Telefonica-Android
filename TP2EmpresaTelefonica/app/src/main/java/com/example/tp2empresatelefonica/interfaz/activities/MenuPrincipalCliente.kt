package com.example.tp2empresatelefonica.interfaz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp2empresatelefonica.clases.cliente.Cliente
import com.example.tp2empresatelefonica.clases.sistema.Sistema
import com.example.tp2empresatelefonica.databinding.MenuPrincipalClienteBinding
import com.example.tp2empresatelefonica.interfaz.adapters.AdapterListaDeLlamadasPorCliente
import java.time.LocalDate

class MenuPrincipalCliente : AppCompatActivity() {

    private lateinit var binding: MenuPrincipalClienteBinding
    private val sistemaPrincipal = Sistema()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MenuPrincipalClienteBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        iniciarMenu()
    }

    private fun iniciarMenu(){

        val intent : Intent = intent
        val userName = intent.getStringExtra("nombre_del_usuario")
        binding.tituloMenuPrincipalCliente.text = "Bienvenido al menu $userName"

        iniciarSistema(sistemaPrincipal)
        iniciarRecyclerView()

    }

    private fun iniciarRecyclerView(){
        binding.rvRegistroDeLlamadaPorCliente.layoutManager = LinearLayoutManager(this)
        binding.rvRegistroDeLlamadaPorCliente.setHasFixedSize(true)
        val customAdapter = sistemaPrincipal.obtenerListaDeLlamadasPorCliente(Cliente(1))
            ?.let { AdapterListaDeLlamadasPorCliente(it) }
        binding.rvRegistroDeLlamadaPorCliente.adapter = customAdapter

    }

    private fun iniciarSistema(sistema: Sistema) {

        sistema.darDeAltaCliente(1,"cliente 1", "", LocalDate.now())

        val listaDeClientes = sistema.obtenerListaDeClientes()
        var contador = 1

        listaDeClientes.forEach { cliente ->

            repeat(20){
            if(contador % 2 == 0) {
                sistema.realizarLlamada(cliente, "06-06-2020", "22:00:00", 550.0, 'I')

            }else {
                sistema.realizarLlamada(cliente, "09-10-2020", "12:00:00", 122.0, 'L')
            }
                contador++
            }

        }


    }
}