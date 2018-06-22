package com.sietecerouno.izzi_user.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.sietecerouno.izzi_user.BaseActivity

import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.adapters.RecyclerAdapterProfile
import com.sietecerouno.izzi_user.modals.DetailReqActivity


class ProfileFragment : Fragment() {


    private val TAG = "GIO"
    lateinit var db: FirebaseFirestore
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var mParam1: String? = null
    private var mParam2: String? = null
    private val data_name: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = FirebaseFirestore.getInstance()
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_profile, container, false)



        val btn_next = v.findViewById<TextView>(R.id.btn_next) as TextView

        val onClickListener : View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.btn_next -> gotoDetail()
            }
        }

        btn_next.setOnClickListener(onClickListener)

        linearLayoutManager = LinearLayoutManager(context)
        val recyclerView = v.findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = linearLayoutManager

        db.collection("pedidos").document(BaseActivity.idReq).get()
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful)
                    {
                        val data_send = task.result.get("empleadas") as ArrayList<String>
                        recyclerView.adapter = RecyclerAdapterProfile(data_send, this.context!!)
                    } else {
                        Log.i(TAG, "Error getting documents: ", task.exception)
                    }
                }

        return v
    }

    private fun gotoDetail()
    {
        val i = Intent(context, DetailReqActivity::class.java)
        startActivity(i)
    }


    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
