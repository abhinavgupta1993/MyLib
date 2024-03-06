package com.abhinav.mylib

import android.content.Context
import android.widget.Toast

class GetText {

    fun generateToast(context: Context, msg: String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

}