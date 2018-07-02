package com.sietecerouno.izzi_user.utils

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.assets.ListenerTab
import com.sietecerouno.izzi_user.sections.HomeActivity
import com.sietecerouno.izzi_user.sections.PreHomeActivity

class LookingForaHelpActivity : HomeActivity()
{


    private lateinit var db : FirebaseFirestore
    private lateinit var listenerTab: ListenerTab


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_looking_fora_help)

        //hide header
        supportActionBar?.hide()
        listenerTab = this


        db = FirebaseFirestore.getInstance()

        lookingFor()

        val btn_cancel = findViewById<TextView>(R.id.btn_cancel)

        val onClickListener : View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.btn_cancel -> cancelReq()
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


                        //Log.i("GIO", idReqHelp_arr.toString())

                        //max size help array
                        if(idReqHelp_arr.size>1)
                        {
                            finish()

                        }
                    }


                })
    }

    private fun cancelReq()
    {
        val i = Intent(this, HomeActivity::class.java)
        startActivity(i)

        finish()

    }
}
