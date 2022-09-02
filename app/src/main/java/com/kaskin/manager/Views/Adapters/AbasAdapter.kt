package com.kaskin.manager.Views.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AbasAdapter(activity: FragmentActivity?) : FragmentStateAdapter(activity!!) {
    private val fragments: MutableList<Fragment> = ArrayList()
    private val titulos: MutableList<String> = ArrayList()

    fun adicionar(fragment: Fragment, tituloAba: String) {
        fragments.add(fragment)
        titulos.add(tituloAba)
    }

    fun getPageTitle(position: Int): String? {
        return titulos[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}