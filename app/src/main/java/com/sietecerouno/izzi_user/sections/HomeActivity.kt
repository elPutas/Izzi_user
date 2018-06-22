package com.sietecerouno.izzi_user.sections


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.adapters.PageAdapter
import com.sietecerouno.izzi_user.fragments.*
import com.sietecerouno.izzi_user.modals.CurrentActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //set header
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("")
        supportActionBar?.setElevation(0.0f)


        val pageAdapter = PageAdapter(supportFragmentManager)

        pageAdapter.add(ServiceFragment.newInstance("service", "test"), "Servicios")
        pageAdapter.add(WhenFragment.newInstance("when", "test"), "Cuando")
        pageAdapter.add(ProfileFragment.newInstance("profile", "test"), "Perfil")
        pageAdapter.add(ReserveFragment.newInstance("service", "test"), "Reservar")

        view_pager.adapter = pageAdapter
        tabs.setupWithViewPager(view_pager)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabs.elevation=0.0f
        }

        for (i in 0 until 4)
        {
            //pageAdapter.add(PageFragment.newInstance(i), "ICOn$i")
            val inflater:LayoutInflater = LayoutInflater.from(this)
            val tv = inflater.inflate(R.layout.custom_txt, null)
            tabs.getTabAt(i)!!.setCustomView(tv)
        }


        // set icons
        //val tabs = findViewById<View>(R.id.tabs) as TabLayout
        //tabs.getTabAt(0)!!.setIcon(R.drawable.home_btn)

    }

    //back btn
    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        return if (item?.itemId == android.R.id.home)
        {
            val i = Intent(this, CurrentActivity::class.java)
            startActivity(i)

            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }


}
