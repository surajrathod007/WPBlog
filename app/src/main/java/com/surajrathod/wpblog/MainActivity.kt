package com.surajrathod.wpblog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.surajrathod.wpblog.fragments.DashboardFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportFragmentManager.beginTransaction().replace(R.id.mainframe,DashboardFragment()).commit()
    }
}