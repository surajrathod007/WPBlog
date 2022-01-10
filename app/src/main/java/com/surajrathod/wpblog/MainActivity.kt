package com.surajrathod.wpblog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.surajrathod.wpblog.fragments.DashboardFragment
import com.surajrathod.wpblog.model.PostCategory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}