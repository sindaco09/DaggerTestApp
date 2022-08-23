package com.indaco.daggertestapp.util.widget

import android.content.Context
import android.os.Build
import androidx.work.*
import com.indaco.daggertestapp.data.worker.widget.MyWidgetWorker
import com.indaco.daggertestapp.ui.screens.widget.EXTRA_WIDGET_IDS
import java.util.concurrent.TimeUnit

object MyWorkerWidgetManager {
    const val WIDGET_UPDATE_WORK = "today_widget_update_work"
    const val WIDGET_PERIODIC_WORK = "today_widget_periodic_update_work"

    inline fun <reified T: CoroutineWorker>updateWidgetDataOnce(context: Context, widgetIds: IntArray?) {
        val request = OneTimeWorkRequestBuilder<T>()
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

    inline fun <reified T: CoroutineWorker>startPeriodicWidgetUpdates(context: Context, widgetIds: IntArray?) {
        val request = PeriodicWorkRequestBuilder<T>(15, TimeUnit.MINUTES)
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