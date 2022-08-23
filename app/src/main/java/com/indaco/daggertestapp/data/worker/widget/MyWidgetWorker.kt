package com.indaco.daggertestapp.data.worker.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.ContextParams
import android.os.Build
import android.os.SystemClock
import android.util.Log
import androidx.work.*
import com.indaco.daggertestapp.ui.screens.widget.EXTRA_WIDGET_IDS
import com.indaco.daggertestapp.ui.screens.widget.MyWidgetData
import com.indaco.daggertestapp.ui.screens.widget.MyWidgetUpdater
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class MyWidgetWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {

    companion object {
        const val WIDGET_UPDATE_WORK = "today_widget_update_work"
        const val WIDGET_PERIODIC_WORK = "today_widget_periodic_update_work"

        fun updateWidgetDataOnce(context: Context, widgetIds: IntArray?) {
            val request = OneTimeWorkRequestBuilder<MyWidgetWorker>()
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                        setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                }
                .setConstraints(Constraints.NONE)
                .setInputData(
                    Data.Builder()
                        .apply { widgetIds?.let { putIntArray(EXTRA_WIDGET_IDS, it) } }
                        .build()
                )
                .build()

            WorkManager.getInstance(context)
                .enqueueUniqueWork(WIDGET_UPDATE_WORK, ExistingWorkPolicy.REPLACE, request)
        }

        fun startPeriodicWidgetUpdates(context: Context, widgetIds: IntArray?) {
            val request = PeriodicWorkRequestBuilder<MyWidgetWorker>(15, TimeUnit.MINUTES)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .setInputData(
                    Data.Builder()
                        .apply { widgetIds?.let { putIntArray(EXTRA_WIDGET_IDS, it) } }
                        .build()
                )
                .build()

            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(WIDGET_PERIODIC_WORK,
                    ExistingPeriodicWorkPolicy.REPLACE,
                    request)
        }

    }

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