package com.sietecerouno.izzi_user.adapters

import android.content.Context
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
class RecyclerAdapterServices(val _names:ArrayList<String>, val context:Context) : RecyclerView.Adapter<RecyclerAdapterServices.MyHolder>()
{

    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {
        holder?.name_txt?.text = _names.get(position)


        fun moreValue()
        {
            holder._value++
            holder._valueStr=holder._value.toString()
        }

        fun lessValue() {
            if(holder._value>0)
            {
                holder._value--
                holder._valueStr=holder._value.toString()
            }

        }

        val onClickListener : View.OnClickListener = View.OnClickListener { view ->

            when(view.id)
            {
                R.id.btnLess -> lessValue()
                R.id.btnMore -> moreValue()
            }

            holder.val_txt?.text = holder._valueStr
        }

        holder?.btn_less.setOnClickListener(onClickListener)
        holder?.btn_more.setOnClickListener(onClickListener)




    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterServices.MyHolder
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
        val btn_less = v.btnLess
        val btn_more = v.btnMore

        var _value       = 0
        var _valueStr    = ""

        var service_arr : ArrayList<String>? = ArrayList()

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

