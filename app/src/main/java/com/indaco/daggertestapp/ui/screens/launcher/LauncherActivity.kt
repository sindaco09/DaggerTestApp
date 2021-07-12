package com.indaco.daggertestapp.ui.screens.launcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.indaco.daggertestapp.ui.screens.landing.LandingActivity

/*
 * Non-Hilt Activity to demo testing Capturing intent starting LandingActivity
 */
class LauncherActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()

        startActivity(Intent(this, LandingActivity::class.java))
    }
}