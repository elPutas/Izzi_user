package com.sietecerouno.izzi_user.sections

import android.os.Bundle
import android.view.MenuItem
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R

class RegisterActivity : BaseActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
