package com.example.newmanager.Controllers

import android.content.Context
import android.widget.Toast

class ToastController {
    fun ToastFeedBackLong(context:Context,message:String){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }
    fun ToastFeedBackShort(context:Context,message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}