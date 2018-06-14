package com.sietecerouno.izzi_user.fragments

import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.adapters.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_services.*


class ServiceFragment : Fragment()
{
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
        val v : View = inflater.inflate(R.layout.fragment_services, container, false)

        println("ServiceFragment")


        linearLayoutManager = LinearLayoutManager(context)
        val recyclerView = v.findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = linearLayoutManager

        db.collection("servicios").get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful)
                    {
                        data_name.clear()
                        for (document in task.result)
                        {
                            data_name.add(document.data.get("titulo").toString())
                        }
                        recyclerView.adapter = RecyclerAdapter(data_name, this.context!!)
                    } else {
                        Log.i(TAG, "Error getting documents: ", task.exception)
                    }
                })








        return v

    }





    companion object
    {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): ServiceFragment {
            val fragment = ServiceFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
