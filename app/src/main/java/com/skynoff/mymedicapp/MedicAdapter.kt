package com.skynoff.mymedicapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MedicAdapter (var listadoMedicos: List<User>):RecyclerView.Adapter<MedicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicViewHolder {
        return MedicViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_listado_medicos,parent,false)
        )
    }

    override fun getItemCount() = listadoMedicos.size


    override fun onBindViewHolder(viewHolder: MedicViewHolder, position: Int) {
        viewHolder.bind(listadoMedicos[position])
    }



}

