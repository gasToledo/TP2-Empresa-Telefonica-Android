package com.example.tp2empresatelefonica.interfaz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.clases.cliente.Cliente
import com.example.tp2empresatelefonica.clases.usuarios.Usuario
import com.example.tp2empresatelefonica.databinding.InicioSesionBinding

class InicioDeSesion : AppCompatActivity() {

    private lateinit var binding : InicioSesionBinding
    private val claveMensaje : String = "nombre_del_usuario"

    private val listaDeUsuarios = mutableListOf(
        Usuario("test","test")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = InicioSesionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.botonIngresar.setOnClickListener {
            iniciarSesion()
        }
    }

    private fun verificarUsuario(user : String, password : String) : Boolean {

        listaDeUsuarios.forEach { usuario ->

            if(usuario.user == user && usuario.password == password){

                return true
            }
        }
        return false
    }

    private fun iniciarSesion() {

        if(verificarUsuario(binding.ingresarUsuario.text.toString(), binding.ingresarContra.text.toString())){
            val intent = Intent(this, MenuPrincipalAdmin::class.java)
            intent.putExtra(claveMensaje, binding.ingresarUsuario.text.toString())
            startActivity(intent)

        }
        else {
            Toast.makeText(this,"Usuario o clave incorrecta",Toast.LENGTH_SHORT).show()
        }
    }


}