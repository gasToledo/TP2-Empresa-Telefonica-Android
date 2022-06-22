package com.example.tp2empresatelefonica.interfaz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tp2empresatelefonica.clases.usuarios.TipoUsuario
import com.example.tp2empresatelefonica.clases.usuarios.Usuario
import com.example.tp2empresatelefonica.databinding.InicioSesionBinding

class InicioDeSesion : AppCompatActivity() {

    private lateinit var binding : InicioSesionBinding
    private val claveMensaje : String = "tipoDeUsuario"

    private val listaDeUsuarios = mutableListOf(
        Usuario("a","a", TipoUsuario.ADMINISTRADOR),
        Usuario("c", "c", TipoUsuario.CLIENTE)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = InicioSesionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.botonIngresar.setOnClickListener {
            iniciarSesion()
        }

        binding.textoRegistrarse.setOnClickListener {
            registrarse()
        }

        binding.textoOlvidoClave.setOnClickListener {
            recuperarClave()
        }
    }


    private fun obtenerUsuario(nombre : String, clave : String) : Usuario? {

        listaDeUsuarios.forEach {
            usuario ->
            if(usuario.user == nombre && usuario.password == clave){
                return usuario
            }
        }
        return null
    }


    private fun iniciarSesion() {

            val auxUsuario : Usuario? = obtenerUsuario(binding.ingresarUsuario.text.toString(), binding.ingresarContra.text.toString())

            if (auxUsuario != null) {


                if (auxUsuario.tipoUsuario == TipoUsuario.ADMINISTRADOR) {

                    val intent = Intent(this, MenuPrincipalAdmin::class.java)
                    intent.putExtra(claveMensaje, binding.ingresarUsuario.text.toString())
                    startActivity(intent)

                }
                else {

                    val intent = Intent(this, MenuPrincipalCliente::class.java)
                    intent.putExtra(claveMensaje, binding.ingresarUsuario.text.toString())
                    startActivity(intent)
                }
            }
        else {
            Toast.makeText(this,"Error de usuario o clave.", Toast.LENGTH_SHORT).show()
            }

    }
    private fun registrarse() {

    }

    private fun recuperarClave(){

    }


}