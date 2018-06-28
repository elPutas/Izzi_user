package com.sietecerouno.izzi_user.sections


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.adapters.PageAdapter
import com.sietecerouno.izzi_user.fragments.*
import com.sietecerouno.izzi_user.modals.CurrentActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.nav_tab.view.*


class HomeActivity : BaseActivity(), OnRefreshListener
{

    lateinit var myDrawer : DrawerLayout

    private val navIcons = intArrayOf(R.drawable.service_tab_icon, R.drawable.when_tab_icon, R.drawable.profile_tab_icon, R.drawable.reserve_tab_icon)
    private val navLabels = intArrayOf(R.string.nav_service_label, R.string.nav_service_label, R.string.nav_service_label, R.string.nav_service_label)

    // active state icon
    private val navIconsActive = intArrayOf(R.drawable.service_tab_icon_on, R.drawable.when_tab_icon_on, R.drawable.profile_tab_icon_on, R.drawable.reserve_tab_icon_on)

    override fun refreshMe() {
        println("update value total: " + idReqTotal_txt.toString())
        super.refreshMe()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //set header
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setTitle("")
        supportActionBar?.setElevation(0.0f)
        supportActionBar?.setCustomView(R.layout.custom_tab)

        myDrawer = findViewById(R.id.drawer_container)
        val btn_chat = findViewById<ImageView>(R.id.btn_chat)
        val btn_open = findViewById<ImageView>(R.id.btn_open)
        val btn_info = findViewById<ImageView>(R.id.btn_info)
        btn_chat.visibility = View.GONE


        val onClickListener = View.OnClickListener { view ->
            when(view.id)
            {
                R.id.btn_open -> openMenu()
                R.id.btn_info -> openCurrent()
            }
        }
        btn_open.setOnClickListener(onClickListener)
        btn_info.setOnClickListener(onClickListener)
        val pageAdapter = PageAdapter(supportFragmentManager)

        pageAdapter.add(ServiceFragment.newInstance("service", "test"), "Servicios")
        pageAdapter.add(WhenFragment.newInstance("when", "test"), "Cuando")
        pageAdapter.add(ProfileFragment.newInstance("profile", "test"), "Perfil")
        pageAdapter.add(ReserveFragment.newInstance("service", "test"), "Reservar")

        val view_pager = findViewById<ViewPager>(R.id.view_pager)
        val tabs = findViewById<TabLayout>(R.id.tabs)



        view_pager.adapter = pageAdapter
        tabs.setupWithViewPager(view_pager)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabs.elevation=0.0f
        }

        for (i in 0 until 4)
        {
            //val tab = LayoutInflater.from(this).inflate(R.layout.nav_tab, null) as LinearLayout

            //pageAdapter.add(PageFragment.newInstance(i), "ICOn$i")
            val inflater:LayoutInflater = LayoutInflater.from(this)
            val tv = inflater.inflate(R.layout.nav_tab, null)

            val tab_label = tv.nav_label as TextView
            val tab_icon = tv.nav_icon

            tab_icon.setImageResource(navIcons[i])
            tab_label.setText(navLabels[i])

            tabs.getTabAt(i)!!.setCustomView(tv)
        }


        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val viewTab : View? = tab?.customView
                val tab_label = viewTab?.nav_label as TextView
                val tab_icon = viewTab?.nav_icon

                tab_icon.setImageResource(navIconsActive[tab.position])

            }
            override fun onTabUnselected(tab:TabLayout.Tab?)
            {
                val viewTab : View? = tab?.customView
                val tab_label = viewTab?.nav_label as TextView
                val tab_icon = viewTab?.nav_icon

                tab_icon.setImageResource(navIcons[tab.position])
            }
            override fun onTabReselected(tab:TabLayout.Tab?) {
            }
        })

    }

    private fun openCurrent()
    {
        val i = Intent(this, CurrentActivity::class.java)
        startActivity(i)
    }

    private fun openMenu()
    {
        if (myDrawer.isDrawerVisible(Gravity.RIGHT)) {
            myDrawer.closeDrawer(Gravity.RIGHT)
        } else {
            myDrawer.openDrawer(Gravity.RIGHT)
        }


    }

    override fun onBackPressed() {
        val i = Intent(this, PreHomeActivity::class.java)
        startActivity(i)
        finish()
    }




}


