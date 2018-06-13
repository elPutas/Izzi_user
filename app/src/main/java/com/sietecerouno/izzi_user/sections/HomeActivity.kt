package com.sietecerouno.izzi_user.sections


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.Toolbar
import android.view.View
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.adapters.PageAdapter
import com.sietecerouno.izzi_user.fragments.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val pageAdapter = PageAdapter(supportFragmentManager)

        pageAdapter.add(ServiceFragment.newInstance("service", "test"), "Servicios")
        pageAdapter.add(WhenFragment.newInstance("when", "test"), "Cuando")
        pageAdapter.add(ProfileFragment.newInstance("profile", "test"), "Perfil")
        pageAdapter.add(ReserveFragment.newInstance("service", "test"), "Reservar")

        /*
        for (i in 0 until 4)
        {
            pageAdapter.add(PageFragment.newInstance(i), "ICOn$i")
        }
        */

        // set icons
        val tabs = findViewById<View>(R.id.tabs) as TabLayout
        view_pager.adapter = pageAdapter
        tabs.setupWithViewPager(view_pager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.home_btn)



    }


}
