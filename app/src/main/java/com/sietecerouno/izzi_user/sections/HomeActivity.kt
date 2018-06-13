package com.sietecerouno.izzi_user.sections


import android.os.Bundle
import com.sietecerouno.izzi_user.BaseActivity
import com.sietecerouno.izzi_user.R
import com.sietecerouno.izzi_user.adapters.PageAdapter
import com.sietecerouno.izzi_user.fragments.PageFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val pageAdapter = PageAdapter(supportFragmentManager)

        for (i in 0 until 4)
        {
            pageAdapter.add(PageFragment.newInstance(i), "Tab$i")
        }

        view_pager.adapter = pageAdapter
        tabs.setupWithViewPager(view_pager)

    }
}
