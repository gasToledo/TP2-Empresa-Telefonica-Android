package com.example.tp2empresatelefonica.interfaz

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2empresatelefonica.R
import com.example.tp2empresatelefonica.clases.cliente.Cliente
import com.example.tp2empresatelefonica.clases.sistema.Sistema

class AdapterListaDeClientes(private val listaClientes : MutableList<Cliente>, private val sistema: Sistema) :
    RecyclerView.Adapter<AdapterListaDeClientes.ViewHolder>() {

    class ViewHolder(view :View) : RecyclerView.ViewHolder(view) {

        val nombreCliente : TextView
        val tipoDeCliente : TextView
        val costoTotalDeLlamadasCliente : TextView

        init {
            nombreCliente = view.findViewById(R.id.rvNombreCliente)
            tipoDeCliente = view.findViewById(R.id.rvTipoDeCliente)
            costoTotalDeLlamadasCliente =  view.findViewById(R.id.rvCostoTotalLlamadasCliente)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_layout_admin, parent,false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nombreCliente.text = listaClientes[position].nombreDeCliente()
        holder.tipoDeCliente.text = listaClientes[position].tipoCliente().name
        holder.costoTotalDeLlamadasCliente.text = sistema.calcularCostoLlamadasCliente(listaClientes[position].codigoDeCliente()).toString()
    }

    override fun getItemCount(): Int  = listaClientes.size

}
