package com.example.newmanager.Views.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class AbasAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val fragments: MutableList<Fragment> = ArrayList()
    private val titulos: MutableList<String> = ArrayList()

    fun adicionar(fragment: Fragment, tituloAba: String) {
        fragments.add(fragment)
        titulos.add(tituloAba)
    }

    override fun getItem(position: Int): Fragment? {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titulos[position]
    }
}