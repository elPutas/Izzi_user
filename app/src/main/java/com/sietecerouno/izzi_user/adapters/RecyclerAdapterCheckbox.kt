package com.sietecerouno.izzi_user.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.sietecerouno.izzi_user.R
import kotlinx.android.synthetic.main.item_custom_checkbox.view.*




/**
 * Created by Gio on 13/06/18.
 */
class RecyclerAdapterCheckbox(val _names:ArrayList<String>, val context: Context) : RecyclerView.Adapter<RecyclerAdapterCheckbox.MyHolder>()
{

    var isChecked: Boolean = false

    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {

        holder?.name_txt?.text = _names.get(position)


        holder.radioBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                holder.radioBox.isChecked = true
                if(holder.lastChecked != null)
                    holder.lastChecked!!.isChecked = false
                notifyDataSetChanged()

            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterCheckbox.MyHolder
    {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_custom_checkbox, parent, false))
    }


    override fun getItemCount(): Int {
        return _names.size
    }


    class MyHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener
    {
        val name_txt = v.name_txt
        val radioBox = v.radioBox
        var lastChecked: CheckBox? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Log.i("GIO", "CLICK")
            radioBox.isChecked = true
            lastChecked = radioBox

        }

        companion object {
            private val NUM = "num"
        }

    }
}