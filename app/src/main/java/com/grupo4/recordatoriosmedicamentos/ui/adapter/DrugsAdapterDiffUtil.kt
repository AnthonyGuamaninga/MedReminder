package com.grupo4.recordatoriosmedicamentos.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.databinding.ItemDrugsBinding
import com.grupo4.recordatoriosmedicamentos.logic.entities.FullInfoDrugsLG

class DrugsAdapterDiffUtil (
    private var onSelectItem: (FullInfoDrugsLG) -> Unit
) : ListAdapter<FullInfoDrugsLG, DrugsAdapterDiffUtil.DrugsVH>(DiffUtilDrugCallback) {

    class DrugsVH(view: View): RecyclerView.ViewHolder(view){
        private var binding: ItemDrugsBinding = ItemDrugsBinding.bind(view)
        fun render(
            item: FullInfoDrugsLG,
            onSelectItem: (FullInfoDrugsLG) -> Unit){
            binding.txtGenericName.text = item.generic_name.toString()
            binding.txtRoute.text = item.route.toString()
            when(item.route){
                "ORAL" -> binding.imgDrug.load(R.drawable.m_oral)
                "INJECTION" -> binding.imgDrug.load(R.drawable.m_injection)
                "INTRAVENOUS" -> binding.imgDrug.load(R.drawable.m_intravenous)
                "SUBCUTANEOUS" -> binding.imgDrug.load(R.drawable.m_subcutaneous)
                "OPHTHALMIC" -> binding.imgDrug.load(R.drawable.m_ophthalmic)
                "TOPICAL" -> binding.imgDrug.load(R.drawable.m_topical)
                "INTRAMUSCULAR" -> binding.imgDrug.load(R.drawable.m_intramuscular)
                else -> binding.imgDrug.load(R.drawable.logo_ma)
            }


            binding.btnInformacion.setOnClickListener({
                onSelectItem(item)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugsVH {
        val inflater = LayoutInflater.from(parent.context)
        return DrugsVH(inflater.inflate(R.layout.item_drugs, parent, false))
    }
    override fun onBindViewHolder(holder: DrugsVH, position: Int) {
        holder.render(getItem(position), onSelectItem)
    }
}


private object DiffUtilDrugCallback : DiffUtil.ItemCallback<FullInfoDrugsLG>() {
    override fun areItemsTheSame(oldItem: FullInfoDrugsLG, newItem: FullInfoDrugsLG): Boolean {
        return oldItem.spl_id == newItem.spl_id
    }

    override fun areContentsTheSame(oldItem: FullInfoDrugsLG, newItem: FullInfoDrugsLG): Boolean {
        return oldItem == newItem
    }

}