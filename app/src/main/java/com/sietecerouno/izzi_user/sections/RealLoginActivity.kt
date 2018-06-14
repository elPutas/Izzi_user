package com.sietecerouno.izzi_user.sections

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R

class RealLoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_login)

        //set header
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Iniciar Sesión")
        supportActionBar?.setElevation(0.0f)

    }


    //back btn
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
