package com.example.tp2empresatelefonica.clases.llamada

import java.time.LocalDate
import java.time.LocalTime

class LlamadaRegular(
    codigo_cliente: Int,
    fecha_llamada: LocalDate,
    hora_llamada: LocalTime,
    duracion_llamada: Double,
    tipo_llamada: Char
) : Llamada(codigo_cliente, fecha_llamada, hora_llamada, duracion_llamada, tipo_llamada) {


    override fun calcularPrecio(): Double {
        val minuto: Double = duracion_llamada / 60.0

        val costoFinalLlamada: Double =
            if (tipo_llamada == 'I') {
                (minuto * 0.05) * 2
            } else {
                (minuto * 0.05)
            }

        return costoFinalLlamada
    }


}