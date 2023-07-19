package com.skynoff.mymedicapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MedicAdapter (private val listadoMedicos: List<String>) : RecyclerView.Adapter<MedicAdapter.MedicViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_medic_adapter,parent,false)
        return MedicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listadoMedicos.size
    }

    override fun onBindViewHolder(holder: MedicViewHolder, position: Int) {
        holder.bind(listadoMedicos[position])
    }
    inner class MedicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreMedico = itemView.findViewById<TextView>(R.id.txtnamemed)

        fun bind(medico: String) {
            nombreMedico.text = medico

        }

    }
}
