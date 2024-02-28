package com.grupo4.recordatoriosmedicamentos.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.MedInfo
import com.grupo4.recordatoriosmedicamentos.databinding.ItemRecetaBinding


class RecetasAdapter() : ListAdapter<MedInfo, RecetasAdapter.RecetaVH>(DiffUtilRecetaCallBack){


    class RecetaVH(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: ItemRecetaBinding = ItemRecetaBinding.bind(view)
        fun render(item: MedInfo) {
            binding.imgDrug.load(R.drawable.logo_ma)
            binding.txtGenericName.text = "Generic Name"
            binding.txtDosis.text = item.dosis + " cada " + item.frecuencia
            binding.txtCaducidad.text = item.caducidad
            binding.txtIndicaciones.text = item.indicaciones
            binding.txtObservaciones.text = item.observaciones
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaVH {
        val inflater = LayoutInflater.from(parent.context)
        return RecetaVH(inflater.inflate(R.layout.item_receta, parent, false))
    }


    override fun onBindViewHolder(holder: RecetaVH, position: Int) {
        holder.render(getItem(position))
    }
}

private object DiffUtilRecetaCallBack : DiffUtil.ItemCallback<MedInfo>(){
    override fun areItemsTheSame(oldItem: MedInfo, newItem: MedInfo): Boolean {
        return (oldItem.id==newItem.id)
    }

    override fun areContentsTheSame(oldItem: MedInfo, newItem: MedInfo): Boolean {
        return (oldItem==newItem)
    }

}