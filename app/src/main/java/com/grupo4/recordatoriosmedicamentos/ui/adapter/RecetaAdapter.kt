package com.grupo4.recordatoriosmedicamentos.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import com.grupo4.recordatoriosmedicamentos.databinding.ItemRecetaBinding

class RecetaAdapter : ListAdapter<Receta, RecetaAdapter.RecetaVH>(DiffUtilRecetaCallBack){

    class RecetaVH(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: ItemRecetaBinding = ItemRecetaBinding.bind(view)
        fun render(item: Receta) {
            var estado : String
            if (item.estado){
                estado="Activo"
            }else{
                estado="Inactivo"
            }
            binding.txtRecetaEstado.text = binding.txtRecetaEstado.text.toString() + estado
            binding.txtRecetaFecha.text = binding.txtRecetaFecha.text.toString() + item.fecha_Registro
            binding.txtRecetaListaM.text = mostrarLista(item.medicamentos)
        }
        fun mostrarLista(lista: List<String>?):String{
            var res =""
            for(item in lista!!){
                res=res+"- "+item+"\n"
            }
            return res
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

private object DiffUtilRecetaCallBack : DiffUtil.ItemCallback<Receta>(){
    override fun areItemsTheSame(oldItem: Receta, newItem: Receta): Boolean {
        return (oldItem.id==newItem.id)
    }

    override fun areContentsTheSame(oldItem: Receta, newItem: Receta): Boolean {
        return (oldItem==newItem)
    }

}