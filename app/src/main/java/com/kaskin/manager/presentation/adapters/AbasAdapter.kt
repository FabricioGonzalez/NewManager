package com.kaskin.manager.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AbasAdapter(activity: FragmentActivity?) : FragmentStateAdapter(activity!!) {
    private val fragments: MutableList<Fragment> = ArrayList()
    private val titles: MutableList<String> = ArrayList()

    fun add(fragment: Fragment, tabTitle: String) {
        if (!titles.contains(tabTitle)) {
            fragments.add(fragment)
            titles.add(tabTitle)
        }
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

        return titles[position]

    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}