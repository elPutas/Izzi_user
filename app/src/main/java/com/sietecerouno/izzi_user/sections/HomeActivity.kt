package com.sietecerouno.izzi_user.sections


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
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
import com.sietecerouno.izzi_user.assets.ListenerTab
import com.sietecerouno.izzi_user.utils.NonSwipeableViewPager
import kotlinx.android.synthetic.main.nav_tab.view.*


open class HomeActivity : BaseActivity(), ListenerTab
{


    lateinit var myDrawer : DrawerLayout
    lateinit var tabs : TabLayout

    private val navIcons = intArrayOf(R.drawable.service_tab_icon, R.drawable.when_tab_icon, R.drawable.profile_tab_icon, R.drawable.reserve_tab_icon)
    private val navLabels = intArrayOf(R.string.nav_service_label, R.string.nav_when_label, R.string.nav_profile_label, R.string.nav_reserve_label)

    // active state icon
    private val navIconsActive = intArrayOf(R.drawable.service_tab_icon_on, R.drawable.when_tab_icon_on, R.drawable.profile_tab_icon_on, R.drawable.reserve_tab_icon_on)


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

        val view_pager = findViewById<NonSwipeableViewPager>(R.id.view_pager)
        tabs = findViewById(R.id.tabs)



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

            override fun onTabSelected(tab: TabLayout.Tab?)
            {
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

    override fun onPostMsg(_msg: String?)
    {
        val dialog = AlertDialog.Builder(this@HomeActivity)
        dialog.setMessage(_msg)
        dialog.show()
    }

    override fun onClickTabDelay(_goto: Int) {
        Handler().postDelayed(
                { onClickTab(_goto) },
                5000)
    }

    override fun onClickTab(_goto: Int)
    {
        val _tabs = findViewById<TabLayout>(R.id.tabs)
        val _tab:  TabLayout.Tab  = _tabs.getTabAt(_goto)!!
        _tab.select()

    }

}


