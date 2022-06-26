package com.example.tp2empresatelefonica.interfaz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.clases.cliente.Cliente
import com.example.tp2empresatelefonica.clases.llamada.Llamada
import java.math.RoundingMode
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class AdapterListaDeLlamadasPorCliente(private val listaDeLlamadas: MutableList<Llamada>):
    RecyclerView.Adapter<AdapterListaDeLlamadasPorCliente.ViewHolder>() {


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val fechaDeLlamada : TextView
        val horarioDeLlamada : TextView
        val duracionDeLaLlamada : TextView
        val costoDeLaLlamada : TextView
        val tipoDeLlamada : TextView
        val contenedorDeDatos : LinearLayout

        init {
            fechaDeLlamada = view.findViewById(R.id.rvFechaDeLlamada)
            horarioDeLlamada = view.findViewById(R.id.rvHorarioDeLlamada)
            duracionDeLaLlamada = view.findViewById(R.id.rvDuracionDeLaLlamada)
            costoDeLaLlamada = view.findViewById(R.id.rvCostoDeLlamada)
            tipoDeLlamada = view.findViewById(R.id.rvTipoDeLlamada)
            contenedorDeDatos = view.findViewById(R.id.contenedorDeRvCliente)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_layout_cliente, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.fechaDeLlamada.text = "Fecha: ${listaDeLlamadas[position].fecha_llamada}"

        holder.horarioDeLlamada.text = "Hora: ${listaDeLlamadas[position].hora_llamada.format(
            DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.ENGLISH)
                .withZone(ZoneId.systemDefault()))}"

        holder.duracionDeLaLlamada.text = "Duracion: ${listaDeLlamadas[position].duracion_llamada}"
        holder.costoDeLaLlamada.text = "Precio: ${listaDeLlamadas[position].calcularPrecio().toBigDecimal().setScale(2,
            RoundingMode.HALF_DOWN).toDouble()}"


        when(listaDeLlamadas[position].tipo_llamada){

            'L' -> {
                holder.tipoDeLlamada.text = "Local"
            }

            'I' -> {
                holder.tipoDeLlamada.text = "Internacional"
            }
            
        }

    }

    override fun getItemCount(): Int = listaDeLlamadas.size
}