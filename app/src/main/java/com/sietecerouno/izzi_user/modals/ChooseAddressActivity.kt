package com.sietecerouno.izzi_user.modals

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.adapters.RecyclerAdapterCheckbox

class ChooseAddressActivity : BaseActivity()
{

    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var db:FirebaseFirestore

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_address)

        //set header
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Selecciona dirección")
        supportActionBar?.setElevation(0.0f)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorAccent) ))

        db = FirebaseFirestore.getInstance()

        linearLayoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = linearLayoutManager


        val data_send :ArrayList<String> = ArrayList()
        val no_items = findViewById<TextView>(R.id.no_items)
        val myLoading = findViewById<ProgressBar>(R.id.myLoading)

        no_items.visibility = View.GONE

        db.collection("direcciones").get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful)
                    {
                        data_send.clear()
                        for (document in task.result)
                        {
                            if(document.get("user").toString() == idUser)
                            {
                                myLoading.visibility = View.GONE
                                data_send.add(document.get("direccion").toString())
                                recyclerView.adapter = RecyclerAdapterCheckbox(data_send, this)
                            }

                        }
                    } else {
                        no_items.visibility = View.VISIBLE
                        myLoading.visibility = View.GONE
                        Log.i("GIO", "Error getting documents: ", task.exception)
                    }
                })

        val btn_new = findViewById<TextView>(R.id.btn_new) as TextView
        val btn_select = findViewById<TextView>(R.id.btn_select) as TextView

        val onClickListener : View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.btn_new -> gotoNew()
                R.id.btn_select -> select()
            }
        }

        btn_new.setOnClickListener(onClickListener)
        btn_select.setOnClickListener(onClickListener)

        checkAddress()
    }

    private fun checkAddress()
    {

    }

    private fun gotoNew()
    {
        val i  = Intent(this, AddAddressActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun select()
    {
        finish()
    }

    //back btn
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            BaseActivity.idAddres = ""
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
