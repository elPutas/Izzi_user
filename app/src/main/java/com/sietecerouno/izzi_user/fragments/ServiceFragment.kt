package com.sietecerouno.izzi_user.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.sietecerouno.izzi_user.BaseActivity

import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.adapters.RecyclerAdapterServices
import com.sietecerouno.izzi_user.assets.ListenerTab
import com.sietecerouno.izzi_user.utils.ReqData
import kotlin.collections.ArrayList


class ServiceFragment : Fragment()
{


    private lateinit var listenerTab:ListenerTab
    private val TAG = "GIO"
    lateinit var db: FirebaseFirestore
    lateinit var btn_next:TextView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var mParam1: String? = null
    private var mParam2: String? = null


    private val data_name: ArrayList<String> = ArrayList()
    private val data_desc: ArrayList<String> = ArrayList()
    private val data_lapse: ArrayList<Number> = ArrayList()
    private val data_subs: ArrayList<String> = ArrayList()
    private val data_value: ArrayList<Number> = ArrayList()


    private lateinit var total_value : TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        db = FirebaseFirestore.getInstance()

        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context?) {
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            listenerTab = activity as ListenerTab
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement ListenerTab")
        }

        super.onAttach(context)
    }

    override fun onResume()
    {
        total_value.text = BaseActivity.idReqTotal_txt.toString()
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v : View = inflater.inflate(R.layout.fragment_services, container, false)

        total_value = v.findViewById<TextView>(R.id.total_value)
        total_value.text = BaseActivity.idReqTotal_txt.toString()

        btn_next = v.findViewById(R.id.btn_next)

        val onClickListener :View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.btn_next -> onNextTab(1)
            }
        }

        btn_next.setOnClickListener(onClickListener)

        linearLayoutManager = LinearLayoutManager(context)
        val recyclerView = v.findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = linearLayoutManager

        db.collection("servicios").get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful)
                    {
                        val obj = object {
                            var title: String = ""
                            var price: Int = 0
                        }

                        clearAll()

                        for (document in task.result)
                        {
                            obj.title = document.data.get("titulo").toString()
                            //obj.price = document.data.get("costo") as Int
                            data_full.add(obj)

                            data_name.add(document.data.get("titulo").toString())
                            data_desc.add(document.data.get("descripcion").toString())
                            data_value.add(document.data.get("costo") as Number)
                            data_lapse.add(document.data.get("horas") as Number)
                            //data_subs.add(document.data.get("subservicios") as String)
                        }

                        recyclerView.adapter = RecyclerAdapterServices(data_name, data_desc, data_value, data_lapse)

                    } else {
                        Log.i(TAG, "Error getting documents: ", task.exception)
                    }
                })

        return v

    }

    private fun onNextTab(s: Int) {

        listenerTab.onClickTab(s)
    }


    private fun clearAll()
    {
        data_name.clear()
        data_full.clear()
        data_desc.clear()
        data_subs.clear()
        data_lapse.clear()
        data_value.clear()
    }


    companion object
    {
        private val data_full: ArrayList<Any> = ArrayList()
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

