package com.sietecerouno.izzi_user.adapters

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sietecerouno.izzi_user.R
import kotlinx.android.synthetic.main.item_custom_service.view.*

/**
 * Created by Gio on 13/06/18.
 */
class RecyclerAdapter(val _names:ArrayList<String>, val context:Context) : RecyclerView.Adapter<RecyclerAdapter.MyHolder>()
{
    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {
        holder?.name_txt?.text = _names.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyHolder
    {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_custom_service, parent, false))
    }


    override fun getItemCount(): Int {
        return _names.size
    }


    class MyHolder(v:View):RecyclerView.ViewHolder(v), View.OnClickListener
    {
        val name_txt = v.name_txt
        val val_txt = v.val_txt

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Log.i("GIO", "CLICK")
        }

        companion object {
            private val NUM = "num"
        }

    }
}

