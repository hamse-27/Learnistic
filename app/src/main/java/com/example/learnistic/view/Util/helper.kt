package com.example.learnistic.view.Util

import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.widget.Toast
import com.example.learnistic.view.HomeActivity

fun Context.toast(message: String)=
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()

fun Context.login(){
    val intent = Intent(this, HomeActivity::class.java).apply{
        flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}


