package com.indaco.daggertestapp.ui.screens.launcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.indaco.daggertestapp.ui.screens.landing.LandingActivity

/*
 * Simple demo activity for testing tracking a new activity being started from another
 * during Instrumentation test
 */
class LauncherActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        startActivity(Intent(this, LandingActivity::class.java))
    }
}