package com.sietecerouno.izzi_user.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.modals.ProfileSelectedActivity

/**
 * Created by Gio on 13/06/18.
 */
class RecyclerAdapterProfile(val _names:ArrayList<String>, val context: Context) : RecyclerView.Adapter<RecyclerAdapterProfile.MyHolder>()
{
    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {
        holder?.name_txt?.text = _names.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterProfile.MyHolder
    {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_custom_profile, parent, false))
    }


    override fun getItemCount(): Int {
        return _names.size
    }


    class MyHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener
    {
        val name_txt = v.findViewById<TextView>(R.id.name_txt)
        //val val_txt = v.val_txt

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            var i = Intent(p0!!.context, ProfileSelectedActivity::class.java)
            p0!!.context.startActivity(i)
        }

        companion object {
            private val NUM = "num"
        }

    }
}