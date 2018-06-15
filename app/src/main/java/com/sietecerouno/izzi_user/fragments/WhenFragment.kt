package com.sietecerouno.izzi_user.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.modals.ChooseAddressActivity


class WhenFragment : Fragment()
{

    private var mParam1: String? = null
    private var mParam2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_when, container, false)


        val onClickListener : View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.addres_item -> gotoNew()

            }
        }

        val addres_btn = v.findViewById<LinearLayout>(R.id.addres_item) as LinearLayout
        addres_btn.setOnClickListener (onClickListener)

        return v
    }

    private fun gotoNew()
    {
        val i = Intent(context, ChooseAddressActivity::class.java)
        startActivity(i)
    }


    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): WhenFragment {
            val fragment = WhenFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
