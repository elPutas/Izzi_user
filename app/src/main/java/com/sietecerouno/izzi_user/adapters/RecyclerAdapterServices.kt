package com.sietecerouno.izzi_user.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.sections.HomeActivity
import com.sietecerouno.izzi_user.utils.ReqData
import kotlin.collections.ArrayList

/**
 * Created by Gio on 13/06/18.
 */
class RecyclerAdapterServices ( val data_name: ArrayList<String>, val data_desc: ArrayList<String>, val data_value: ArrayList<Number>, val data_lapse: ArrayList<Number>) : RecyclerView.Adapter<RecyclerAdapterServices.MyHolder>()
//class RecyclerAdapterServices (val _data: ReqData, val listener: (Int) -> Unit) : RecyclerView.Adapter<RecyclerAdapterServices.MyHolder>()
{


    lateinit var total : TextView


    override fun onBindViewHolder(holder: MyHolder, position: Int)
    {


        val _data_value : Number = data_value.get(position)
        val _data_lapse : Number = data_lapse.get(position)

        //holder.bind(data_name, position, listener)
        holder.name_txt?.text = data_name.get(position)

        if(_data_lapse is Number)
            holder.lapse = _data_lapse

        if(_data_value is Number)
            holder.price = _data_value.toInt()


        //holder.bind(data_name, listener)

        fun moreValue()
        {
            holder._value++
            holder._valueStr=holder._value.toString()

            BaseActivity.idReqService_arr.add(holder.name_txt?.text.toString())
            BaseActivity.idReqServiceValue_arr.add(holder.price)

        }

        fun lessValue() {
            if(holder._value>0)
            {
                holder._value--
                holder._valueStr=holder._value.toString()
                BaseActivity.idReqService_arr.remove(holder.name_txt?.text.toString())
                BaseActivity.idReqServiceValue_arr.remove(holder.price)

            }


        }

        val onClickListener : View.OnClickListener = View.OnClickListener { view ->

            when(view.id)
            {
                R.id.btnLess -> lessValue()
                R.id.btnMore -> moreValue()
            }

            holder.val_txt?.text = holder._valueStr
            calculateTotal()
        }

        holder.btn_less.setOnClickListener(onClickListener)
        holder.btn_more.setOnClickListener(onClickListener)

    }


    private fun calculateTotal()
    {
        BaseActivity.idReqTotal_txt = 0
        for (num in BaseActivity.idReqServiceValue_arr)
        {
            BaseActivity.idReqTotal_txt += num
        }
        notifyDataSetChanged()

        println("TOTAL: "+BaseActivity.idReqTotal_txt)
        total.text = BaseActivity.idReqTotal_txt.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterServices.MyHolder
    {
        total = (parent.context as HomeActivity).findViewById(R.id.total_value)
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_custom_service, parent, false))
    }


    override fun getItemCount(): Int = data_name.size





    class MyHolder(v:View):RecyclerView.ViewHolder(v), View.OnClickListener
    {
        val name_txt = v.findViewById<TextView>(R.id.name_txt)
        val val_txt = v.findViewById<TextView>(R.id.val_txt)
        val btn_less = v.findViewById<TextView>(R.id.btnLess)
        val btn_more = v.findViewById<TextView>(R.id.btnMore)

        val total = v.findViewById<TextView>(R.id.total)

        var _value       = 0
        var _valueStr    = ""

        var price : Int  = 0
        var lapse : Number  = 0



        var service_arr : ArrayList<String>? = ArrayList()

        init {
            v.setOnClickListener(this)
        }

        /*
        fun bind(listOfData: ArrayList<ReqData>, listener: ContentListener) {
            val dataListin2 = listOfData[adapterPosition]

            // this is the click listener. It calls the onItemClicked interface method implemented in the Activity
            itemView.setOnClickListener {
                listener.onItemClicked(listOfData.get(adapterPosition))
            }
        }
        */

        override fun onClick(p0: View?) {
            total.text = BaseActivity.idReqTotal_txt.toString()
            Log.i("GIO", "CLICK")
        }

        companion object {
            private val NUM = "num"
        }

    }


}





