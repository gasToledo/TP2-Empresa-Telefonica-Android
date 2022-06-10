package com.example.tp2empresatelefonica.clases.llamada

import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalTime

abstract class Llamada(
    private val  codigo_cliente : Int,
    private val fecha_llamada: LocalDate,
    private val hora_llamada: LocalTime,
    protected val duracion_llamada: Double,
    protected val tipo_llamada: Char
) {

    abstract fun calcularPrecio(): Double

    override fun toString(): String {
        return """
            
            
            Fecha de llamada : $fecha_llamada
            Hora de llamada : $hora_llamada
            Duracion de llamada : $duracion_llamada
            Tipo de llamada : $tipo_llamada
            Precio de llamada : ${calcularPrecio().toBigDecimal().setScale(2,RoundingMode.HALF_DOWN).toDouble()}
            
            """.trimIndent()
    }
}