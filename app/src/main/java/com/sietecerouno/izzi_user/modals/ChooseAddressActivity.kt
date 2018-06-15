package com.sietecerouno.izzi_user.modals

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R

class ChooseAddressActivity : BaseActivity()
{

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


        val btn_new = findViewById<TextView>(R.id.btn_new) as TextView

        val onClickListener : View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.btn_new -> gotoNew()
                R.id.btn_select -> select()

            }
        }

        btn_new.setOnClickListener(onClickListener)
    }

    private fun gotoNew()
    {
        val i  = Intent(this, AddAddressActivity::class.java)
        startActivity(i)
    }

    private fun select()
    {

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
