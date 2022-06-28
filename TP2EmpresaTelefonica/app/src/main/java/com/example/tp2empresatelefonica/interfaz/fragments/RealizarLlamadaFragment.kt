package com.example.tp2empresatelefonica.interfaz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.clases.sistema.Sistema
import com.example.tp2empresatelefonica.databinding.FragmentRealizarLlamadaBinding
import com.example.tp2empresatelefonica.repositorios.ClientesRepository
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class RealizarLlamadaFragment : Fragment() {



    private lateinit var  binding : FragmentRealizarLlamadaBinding
    private val sistema = Sistema()
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRealizarLlamadaBinding.inflate(inflater,container,false)
        sistema.iniciarClientesPredeterminados()
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val navController : NavController = view.findNavController()
        val claveMensaje = "USER_ID"

        val bundle = arguments
        val userId = bundle!!.getInt(claveMensaje)

        println("llego $userId")

        binding.ingresarFechaLlamada.setOnClickListener {
            val fecha = DatePickerFragment {year, month, day -> mostrarFecha(year, month, day)}
            fecha.show(parentFragmentManager, "DatePicker")
        }

        binding.ingresarHorarioLlamada.setOnClickListener {
            val horario = TimePickerFragment {hour, minute -> mostrarHorario(hour, minute)}
            horario.show(parentFragmentManager,"TimePicker")
        }

        binding.hacerLlamada.setOnClickListener {

            val fechaSeleccionada : String = binding.ingresarFechaLlamada.text.toString()
            val horarioSeleccionado : String = binding.ingresarHorarioLlamada.text.toString()


                val cliente = sistema.obtenerCliente(userId)

                sistema.realizarLlamada(cliente,
                fechaSeleccionada,
                horarioSeleccionado,
                binding.ingresarDuracionLlamada.text.toString().toDouble(),
                binding.ingresarTipoLlamada.text.toString().first()
            )

            Toast.makeText(binding.root.context, "Llamada OK", Toast.LENGTH_SHORT).show()
            }

    }

    private fun mostrarHorario(hour: Int, minute: Int) {

        binding.ingresarHorarioLlamada.setText(formatTime(hour,minute))

    }

    private fun mostrarFecha(year: Int, month: Int, day: Int) {

        binding.ingresarFechaLlamada.setText(formatDate(year,month,day))
    }

    private fun formatDate(year: Int,month: Int,day: Int) : String{
        val sanitizeMonth = month + 1

        return LocalDate.of(year,sanitizeMonth,day).format(dateFormatter)
    }

    private fun formatTime(hour :Int, minute: Int) : String{

        return LocalTime.of(hour,minute).format(timeFormatter)
    }

}