package com.sietecerouno.izzi_user.sections

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide header
        supportActionBar?.hide()


        val btn_register = findViewById<TextView>(R.id.btn_register)
        val btn_fb = findViewById<TextView>(R.id.btn_fb)
        val btn_login = findViewById<TextView>(R.id.btn_login)

        val onClickListener :View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.btn_register -> gotoReg()
                R.id.btn_login -> gotoLog()
                R.id.btn_fb -> gotoFB()
            }


        }



        btn_register.setOnClickListener( onClickListener )
        btn_fb.setOnClickListener( onClickListener )
        btn_login.setOnClickListener( onClickListener )


    }

    private fun gotoReg()
    {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
        finish()
    }
    private fun gotoLog()
    {
        val i = Intent(this, RealLoginActivity::class.java)
        startActivity(i)
        finish()
    }
    private fun gotoFB()
    {
        print("click fb")
    }
}
