package com.sietecerouno.izzi_user.utils

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.adapters.RecyclerAdapterProfile

class LookingForaHelpActivity : BaseActivity()
{


    lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_looking_fora_help)

        //hide header
        supportActionBar?.hide()

        db = FirebaseFirestore.getInstance()

        lookingFor()

        val btn_cancel = findViewById<TextView>(R.id.btn_cancel)

        val onClickListener : View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.btn_cancel -> canceReq()
            }
        }

        btn_cancel.setOnClickListener( onClickListener )
    }

    private fun lookingFor()
    {
        db.collection("pedidos").document(idReq)
                .addSnapshotListener(this, EventListener { documentSnapshots, e ->
                    if (e != null) {
                        Log.i(TAG, "Listen failed!", e)
                        return@EventListener
                    }

                    idReqHelp_arr.clear()

                    if(documentSnapshots.exists())
                    {
                        var arrTemp :ArrayList<String>  = documentSnapshots.get("empleadas") as ArrayList<String>

                        for (doc in arrTemp)
                        {
                            idReqHelp_arr.add(doc)
                        }

                        Log.i("GIO", idReqHelp_arr.toString())
                    }


                })
    }

    private fun canceReq()
    {
        finish()
    }
}
