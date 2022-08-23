package com.indaco.daggertestapp.data.repositories

import com.indaco.daggertestapp.data.storage.cache.WidgetCache
import com.indaco.daggertestapp.ui.screens.widget.MyWidgetData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WidgetRepository @Inject constructor(private val widgetCache: WidgetCache) {

    var myWidgetData: MyWidgetData
        get() = widgetCache.myData
        set(value) { widgetCache.myData = value }

}