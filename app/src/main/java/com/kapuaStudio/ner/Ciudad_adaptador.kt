package com.kapuaStudio.ner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.kapuaStudio.ner.data.ciudadesPrincipales
import kotlinx.android.synthetic.main.item_ciudad.view.*

class Ciudad_adaptador  (private val mContext: Context, private val ciudadesPLista: List<ciudadesPrincipales>):
    ArrayAdapter<ciudadesPrincipales>(mContext, 0, ciudadesPLista)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_ciudad, parent, false)

        val ciudad = ciudadesPLista[position]
        layout.item_nombre.text = ciudad.nombre

        return layout
    }
}