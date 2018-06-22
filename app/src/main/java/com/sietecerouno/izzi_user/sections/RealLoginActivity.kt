package com.sietecerouno.izzi_user.sections

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R

class RealLoginActivity : BaseActivity()
{
    lateinit var myLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_login)

        //set header
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Iniciar Sesión")
        supportActionBar?.setElevation(0.0f)

        val btn_next = findViewById<TextView>(R.id.btn_next)

        val onClickListener : View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.btn_next -> checkUser()

            }
        }

        btn_next.setOnClickListener(onClickListener)

        myLoading = findViewById<ProgressBar>(R.id.myLoading)
        myLoading.visibility = View.GONE
    }

    private fun checkUser()
    {
        val user = findViewById<EditText>(R.id.user)
        val pass = findViewById<EditText>(R.id.pass)

        if (user.text.toString() != "" && pass.text.toString() != "")
        {
            myLoading.visibility = View.VISIBLE

            FirebaseAuth.getInstance().signInWithEmailAndPassword(user.text.toString(), pass.text.toString())
                    .addOnCompleteListener(this){
                        task -> gotoHome()
                    }
        }



    }

    private fun gotoHome()
    {
        val i = Intent(this, HomeActivity::class.java)
        startActivity(i)
        finish()
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
