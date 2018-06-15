package com.sietecerouno.izzi_user.modals

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.sections.PreHomeActivity

class AddAddressActivity : BaseActivity()
{
    val TAG = "GIO"
    lateinit var db :FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        db = FirebaseFirestore.getInstance()

        //set header
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Nueva dirección")
        supportActionBar?.setElevation(0.0f)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorAccent) ))


        val onClickListener : View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.btn_new -> gotoNew()

            }
        }

        val btn_new = findViewById<TextView>(R.id.btn_new)
        btn_new.setOnClickListener(onClickListener)
    }

    private fun gotoNew()
    {
        val city = findViewById<EditText>(R.id.city_txt)
        val cll1 = findViewById<EditText>(R.id.cll1_txt)
        val cll2 = findViewById<EditText>(R.id.cll2_txt)
        val num1 = findViewById<EditText>(R.id.num1_txt)
        val num2 = findViewById<EditText>(R.id.num2_txt)
        val info = findViewById<EditText>(R.id.info_txt)

        val fullAddress = cll1.text.toString() + cll2.text.toString() + " # " + num1.text.toString() + " - " + num2.text.toString() + " " + info.text.toString()

        val items = HashMap<String, Any>()

        items.put("direccion", fullAddress)
        items.put("ciudad", city.text.toString())
        items.put("user", idUser)

        db.collection("direcciones")
                .add(items)
                .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                    Log.i(TAG, "DocumentSnapshot written with ID: " + documentReference.id)
                    finish()
                })
                .addOnFailureListener(OnFailureListener {
                    e -> Log.w(TAG, "Error adding document", e)
                })

    }

    //back btn
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
