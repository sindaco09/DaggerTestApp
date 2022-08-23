package com.indaco.daggertestapp.data.storage.cache

import android.content.SharedPreferences
import com.indaco.daggertestapp.ui.screens.widget.MyWidgetData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WidgetCache @Inject constructor(private val sp: SharedPreferences) {

    companion object {
        const val MY_WIDGET_DATA_KEY = "MY_WIDGET_DATA_KEY"
    }
    var myData: MyWidgetData
        get() = MyWidgetData(sp.getString(MY_WIDGET_DATA_KEY, "")?:"")
        set(value) = sp.edit().putString(MY_WIDGET_DATA_KEY, value.data).apply()

}