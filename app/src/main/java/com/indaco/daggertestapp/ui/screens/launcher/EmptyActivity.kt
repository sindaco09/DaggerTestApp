package com.indaco.daggertestapp.ui.screens.launcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.indaco.daggertestapp.R

/*
 * Empty activity to act as a stub during an End-to-End test.
 * Purpose is to not have Hilt to allow for simple demos
 */
class EmptyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
    }
}