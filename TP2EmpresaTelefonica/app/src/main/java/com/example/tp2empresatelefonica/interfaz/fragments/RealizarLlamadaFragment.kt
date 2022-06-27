package com.example.tp2empresatelefonica.interfaz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.databinding.FragmentRealizarLlamadaBinding
import com.example.tp2empresatelefonica.repositorios.LlamadasRepository
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class RealizarLlamadaFragment : Fragment() {



    private lateinit var  binding : FragmentRealizarLlamadaBinding
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentRealizarLlamadaBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val navController : NavController = view.findNavController()

        binding.ingresarFechaLlamada.setOnClickListener {
            val fecha = DatePickerFragment {year, month, day -> mostrarFecha(year, month, day)}
            fecha.show(parentFragmentManager, "DatePicker")
        }

        binding.ingresarHorarioLlamada.setOnClickListener {
            val horario = TimePickerFragment {hour, minute -> mostrarHorario(hour, minute)}
            horario.show(parentFragmentManager,"TimePicker")
        }


        binding.volverMenuCliente.setOnClickListener {

            navController.navigate(R.id.action_realizarLlamadaFragment_to_menuCliente)
        }

        binding.hacerLlamada.setOnClickListener {

            val fechaSeleccionada : String = binding.ingresarFechaLlamada.text.toString()
            val horarioSeleccionado : String = binding.ingresarHorarioLlamada.text.toString()

            LlamadasRepository.agregarLlamada(1,
                LocalDate.parse(fechaSeleccionada, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse(horarioSeleccionado,DateTimeFormatter.ofPattern("HH:mm")),
                binding.ingresarDuracionLlamada.text.toString().toDouble(),
                binding.ingresarTipoLlamada.text.toString().first()
            )

        }


        /*
        binding.volverMenuCliente.setOnClickListener {

            navController.navigate(R.id.action_realizarLlamadaFragment_to_menuCliente)
        }

        binding.hacerLlamada.setOnClickListener {

            if(binding.ingresarFechaLlamada.text.isNullOrEmpty()
                || binding.ingresarHorarioLlamada.text.isNullOrEmpty()
                || binding.ingresarDuracionLlamada.text.isNullOrEmpty()
                || binding.ingresarTipoLlamada.text.isNullOrEmpty() ){

                Toast.makeText(binding.root.context, "Ingrese datos validos porfavor.", Toast.LENGTH_SHORT).show()
            }
            else if(LlamadasRepository.agregarLlamada(1,
                    LocalDate.parse(binding.ingresarFechaLlamada.text.toString()),
                    LocalTime.parse(binding.ingresarHorarioLlamada.text.toString()),
                    binding.ingresarDuracionLlamada.text.toString().toDouble(),
                    binding.ingresarTipoLlamada.text.first())){

                Toast.makeText(binding.root.context, "Se ha agendado la llamada realizada el ${binding.ingresarFechaLlamada.text}", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_realizarLlamadaFragment_to_menuCliente)
            }
            else{
                Toast.makeText(binding.root.context, "Error al realizar llamada", Toast.LENGTH_SHORT).show()
            }
        }
        */

        binding.volverMenuCliente.setOnClickListener {

            navController.navigate(R.id.action_realizarLlamadaFragment_to_menuCliente)
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