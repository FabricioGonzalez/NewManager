package com.kaskin.manager.presentation.adapters.itemclick

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener


internal class RecyclerTouchListener(
    context: Context?,
    recycleView: RecyclerView,
    private val clicklistener: ClickListener?
) :
    OnItemTouchListener {
    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                val child: View? = recycleView.findChildViewUnder(e.x, e.y)
                if (child != null && clicklistener != null) {
                    clicklistener.onClick(child, recycleView.getChildAdapterPosition(child))
                }

                return true
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                val child: View? = recycleView.findChildViewUnder(e.x, e.y)
                if (child != null && clicklistener != null ) {
                    clicklistener.onDoubleClick(child, recycleView.getChildAdapterPosition(child))
                }

                return true
            }

            override fun onLongPress(e: MotionEvent) {
                val child: View? = recycleView.findChildViewUnder(e.x, e.y)
                if (child != null && clicklistener != null) {
                    clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child))
                }
            }
        })
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val child: View? = rv.findChildViewUnder(e.x, e.y)
        if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
            clicklistener.onClick(child, rv.getChildAdapterPosition(child))
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}