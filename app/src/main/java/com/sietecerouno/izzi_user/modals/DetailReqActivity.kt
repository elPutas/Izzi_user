package com.sietecerouno.izzi_user.modals


import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R

class DetailReqActivity : BaseActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_req)

        //set header
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Detalle servicio")
        supportActionBar?.setElevation(0.0f)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorAccent) ))
    }

    //back btn
    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        return if (item?.itemId == android.R.id.home)
        {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
