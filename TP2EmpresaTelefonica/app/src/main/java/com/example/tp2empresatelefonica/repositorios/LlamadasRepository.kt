package com.example.tp2empresatelefonica.repositorios

import com.example.tp2empresatelefonica.clases.llamada.Llamada
import com.example.tp2empresatelefonica.clases.llamada.LlamadaFinDeSemana
import com.example.tp2empresatelefonica.clases.llamada.LlamadaNocturna
import com.example.tp2empresatelefonica.clases.llamada.LlamadaRegular
import java.time.LocalDate
import java.time.LocalTime

object LlamadasRepository {

    private val listaDeLlamadas = mutableListOf<Llamada>()


    init {
        listaDeLlamadas.addAll(
            listOf(

                LlamadaNocturna(1, LocalDate.of(2020,5,5), LocalTime.of(22,56,20),200.0,'L'),
                LlamadaRegular(1, LocalDate.of(2022,3,13), LocalTime.of(15,40,30),400.0,'I'),
                LlamadaRegular(1, LocalDate.of(2022,3,13), LocalTime.of(16,40,30),400.0,'L'),
                LlamadaFinDeSemana(1, LocalDate.of(2021,6,6), LocalTime.of(10,25,10),100.0,'I'),
                LlamadaRegular(1, LocalDate.of(2022,3,13), LocalTime.of(15,40,30),400.0,'I'),
                LlamadaRegular(1, LocalDate.of(2022,3,13), LocalTime.of(16,40,30),400.0,'L'),
                LlamadaFinDeSemana(1, LocalDate.of(2021,6,6), LocalTime.of(10,25,10),100.0,'I'),
                LlamadaRegular(1, LocalDate.of(2022,3,13), LocalTime.of(15,40,30),400.0,'I'),
                LlamadaRegular(1, LocalDate.of(2022,3,13), LocalTime.of(16,40,30),400.0,'L'),
                LlamadaFinDeSemana(1, LocalDate.of(2021,6,6), LocalTime.of(10,25,10),100.0,'I'),
                LlamadaRegular(1, LocalDate.of(2022,3,13), LocalTime.of(15,40,30),400.0,'I'),
                LlamadaRegular(1, LocalDate.of(2022,3,13), LocalTime.of(16,40,30),400.0,'L'),
                LlamadaFinDeSemana(1, LocalDate.of(2021,6,6), LocalTime.of(10,25,10),100.0,'I'),
                LlamadaRegular(1, LocalDate.of(2022,3,13), LocalTime.of(15,40,30),400.0,'I'),
                LlamadaRegular(1, LocalDate.of(2022,3,13), LocalTime.of(16,40,30),400.0,'L'),
                LlamadaFinDeSemana(1, LocalDate.of(2021,6,6), LocalTime.of(10,25,10),100.0,'I')
            )
        )
    }

    fun obtenerListaDeLlamadas(): MutableList<Llamada> {

        return listaDeLlamadas
    }

    fun agregarLlamada(codigoCliente : Int, fecha : LocalDate, horario : LocalTime, duracion : Double, tipoLlamada : Char)  {

            listaDeLlamadas.add(
                LlamadaRegular(
                    codigoCliente,
                    fecha,
                    horario,
                    duracion,
                    tipoLlamada
                )
            )

    }

    fun agregarLlamada(llamada: Llamada) {

        listaDeLlamadas.add(llamada)
    }


}