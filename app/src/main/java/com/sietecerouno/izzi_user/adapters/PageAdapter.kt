package com.sietecerouno.izzi_user.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Gio on 13/06/18.
 */
class PageAdapter (fm:FragmentManager) :FragmentPagerAdapter (fm)
{
    private val tabNames : ArrayList<String>
    private val fragments : ArrayList<Fragment>

    init {
        tabNames = ArrayList()
        fragments = ArrayList()
    }

    fun add(fragment: Fragment, title:String)
    {
        tabNames.add(title)
        fragments.add(fragment)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames[position]
    }

}