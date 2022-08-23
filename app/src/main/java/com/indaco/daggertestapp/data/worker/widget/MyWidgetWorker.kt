package com.indaco.daggertestapp.data.worker.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.indaco.daggertestapp.ui.screens.widget.EXTRA_WIDGET_IDS
import com.indaco.daggertestapp.ui.screens.widget.MyWidgetData
import com.indaco.daggertestapp.ui.screens.widget.MyWidgetUpdater
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyWidgetWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val appWidgetIds = inputData.getIntArray(EXTRA_WIDGET_IDS)
        val time = System.currentTimeMillis().toString()
        val smallerTime = time.substring(time.length - 4, time.length)
        Log.d("TAG","time: $time\n\tsmallerTime: $smallerTime")

        val widgetData = MyWidgetData(smallerTime)
        withContext(Dispatchers.Main) {

            val appWidgetManager = AppWidgetManager.getInstance(applicationContext)
            appWidgetIds?.forEach {
                MyWidgetUpdater.updateAppWidget(applicationContext, appWidgetManager, it, widgetData)
            }
        }

        return Result.success()
    }
}