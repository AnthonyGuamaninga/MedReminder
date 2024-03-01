package com.grupo4.recordatoriosmedicamentos.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.data.network.entities.fda.Product


class ProductAdapter(
    context: Context,
    product: List<Product>
): ArrayAdapter<Product>(context, 0, product) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val vista = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_product, parent, false)

        getItem(position)?.let {
            vista.findViewById<TextView>(R.id.tvBrandName).apply{
                text = it.brand_name
            }
        }
    return vista
    }
}