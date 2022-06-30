package com.example.tp2empresatelefonica.interfaz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tp2empresatelefonica.clases.usuarios.TipoUsuario
import com.example.tp2empresatelefonica.databinding.InicioSesionBinding
import com.example.tp2empresatelefonica.repositorios.UsersRepository

class InicioDeSesion : AppCompatActivity() {

    private lateinit var binding: InicioSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = InicioSesionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Toast.makeText(this,"PARA INICIAR COMO ADMIN [USUARIO: ADMIN, CLAVE: ADMIN]", Toast.LENGTH_LONG).show()
        Toast.makeText(this,"PARA INICIAR COMO CLIENTE [USUARIO/CLAVE: C1]", Toast.LENGTH_LONG).show()

        binding.botonIngresar.setOnClickListener {
            iniciarSesion()
        }

    }

    private fun iniciarSesion() {


        val claveMensaje = "USER_ID"
        val bundle = Bundle()


        val usuarioFinal = UsersRepository.obtenerUsuario(
            binding.ingresarUsuario.text.toString(),
            binding.ingresarContra.text.toString()
        )

        if (usuarioFinal != null) {

            if (usuarioFinal.tipoUsuario == TipoUsuario.ADMINISTRADOR) {

                val intent = Intent(this, MenuPrincipalAdmin::class.java)
                startActivity(intent)

            } else {

                bundle.putInt(claveMensaje, usuarioFinal.idUsuario)
                val intent = Intent(this, MenuPrincipalCliente::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        } else {
            Toast.makeText(this, "Error de usuario o clave.", Toast.LENGTH_SHORT).show()
        }

    }


}

