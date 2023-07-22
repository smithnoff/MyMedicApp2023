package com.skynoff.mymedicapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.skynoff.mymedicapp.databinding.ItemListadoMedicosBinding

class MedicViewHolder(view:View):RecyclerView.ViewHolder(view) {

private val binding = ItemListadoMedicosBinding.bind(view)

    fun bind(listadoMedicos: User){
        binding.txtNombreDoc.text = listadoMedicos.name
        binding.txtEspecialidadDoc.text = listadoMedicos.username
        binding.txtExperienciaDoc.text = listadoMedicos.email

    }
}