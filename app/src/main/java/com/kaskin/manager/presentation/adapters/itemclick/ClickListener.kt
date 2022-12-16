package com.kaskin.manager.presentation.adapters.itemclick

import android.view.View

interface ClickListener {
    fun onClick(view: View?, position: Int)
    fun onDoubleClick(view: View?, position: Int)
    fun onLongClick(view: View?, position: Int)
}