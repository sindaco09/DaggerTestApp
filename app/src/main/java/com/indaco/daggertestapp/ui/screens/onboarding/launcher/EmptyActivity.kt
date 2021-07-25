package com.indaco.daggertestapp.ui.screens.onboarding.launcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.indaco.daggertestapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmptyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
    }
}