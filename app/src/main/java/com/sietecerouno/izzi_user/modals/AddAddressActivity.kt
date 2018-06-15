package com.sietecerouno.izzi_user.modals

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R

class AddAddressActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        //set header
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Nueva dirección")
        supportActionBar?.setElevation(0.0f)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorAccent) ))
    }
}
