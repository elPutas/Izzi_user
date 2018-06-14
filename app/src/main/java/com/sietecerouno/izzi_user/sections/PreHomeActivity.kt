package com.sietecerouno.izzi_user.sections


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R

class PreHomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_home)

        //set header
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("")
        supportActionBar?.setElevation(0.0f)


        val btn_service = findViewById<TextView>(R.id.btn_service)
        val btn_pack = findViewById<TextView>(R.id.btn_pack)

        val onClickListener : View.OnClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.btn_service -> gotoService()
                R.id.btn_pack -> gotoPack()

            }


        }

        btn_service.setOnClickListener( onClickListener )
        btn_pack.setOnClickListener( onClickListener )

    }

    private fun gotoPack()
    {

    }

    private fun gotoService()
    {
        val i = Intent(this, HomeActivity::class.java)
        startActivity(i)
        finish()
    }
}
