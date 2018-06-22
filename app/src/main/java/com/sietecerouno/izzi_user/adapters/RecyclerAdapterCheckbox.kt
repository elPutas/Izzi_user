package com.sietecerouno.izzi_user.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R
import kotlinx.android.synthetic.main.item_custom_checkbox.view.*




/**
 * Created by Gio on 13/06/18.
 */
class RecyclerAdapterCheckbox(val _names:ArrayList<String>, val context: Context) : RecyclerView.Adapter<RecyclerAdapterCheckbox.MyHolder>()
{

    var lastChecked: CheckBox? = null
    var isCheckedStr: String = ""

    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {

        holder?.name_txt?.text = _names.get(position)


        holder.radioBox.setOnCheckedChangeListener { buttonView, isChecked ->

            if(lastChecked != null)
            {
                lastChecked!!.isChecked = false
            }

            if (isChecked)
                BaseActivity.idAddres = _names.get(position)


            notifyDataSetChanged()
            lastChecked = holder.radioBox
        }



    }



    fun CheckBox.setCheckedWithoutAnimation(checked: Boolean) {
        val beforeVisibility = visibility
        visibility = View.INVISIBLE
        isChecked = checked
        visibility = beforeVisibility
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