package com.kaskin.manager.Views.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AbasAdapter(activity: FragmentActivity?) : FragmentStateAdapter(activity!!) {
    private val fragments: MutableList<Fragment> = ArrayList()
    private val titles: MutableList<String> = ArrayList()

    fun add(fragment: Fragment, tabTitle: String) {
        if (!fragments.contains(fragment))
            fragments.add(fragment)
        if (!titles.contains(tabTitle))
            titles.add(tabTitle)
    }

    fun clear() {
        fragments.clear()
        titles.clear()
    }

    fun refreshFragment(index: Int) {
        if (fragments[index] is Updatable)
            (fragments[index] as Updatable).Update()

        notifyItemChanged(index)
    }

    fun getPageTitle(position: Int): String? {
        if (position != titles.size)
            return titles[position]
        return null
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}