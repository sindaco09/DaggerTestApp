package com.indaco.daggertestapp.ui.screens.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.work.WorkManager
import com.indaco.daggertestapp.data.worker.widget.MyHiltWidgetWorker
import com.indaco.daggertestapp.ui.screens.widget.MyWidgetUpdater.updateAppWidget
import com.indaco.daggertestapp.util.widget.MyWorkerWidgetManager.WIDGET_PERIODIC_WORK
import com.indaco.daggertestapp.util.widget.MyWorkerWidgetManager.WIDGET_UPDATE_WORK
import com.indaco.daggertestapp.util.widget.MyWorkerWidgetManager.startPeriodicWidgetUpdates
import com.indaco.daggertestapp.util.widget.MyWorkerWidgetManager.updateWidgetDataOnce
import dagger.hilt.android.AndroidEntryPoint

const val ACTION_UPDATE_MY_WIDGET = "ACTION_UPDATE_MY_WIDGET"
const val EXTRA_WIDGET_ID = "EXTRA_WIDGET_ID"
const val EXTRA_WIDGET_IDS = "EXTRA_WIDGET_IDS"

@AndroidEntryPoint
class MyWidget : AppWidgetProvider() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            ACTION_UPDATE_MY_WIDGET ->
                context?.let {
                    var widgetIds: IntArray? =
                        intArrayOf(intent.getIntExtra(EXTRA_WIDGET_ID,
                            AppWidgetManager.INVALID_APPWIDGET_ID))
                    if (widgetIds?.first() == AppWidgetManager.INVALID_APPWIDGET_ID)
                        widgetIds = getWidgetIds(it)

                    updateWidgetDataOnce<MyHiltWidgetWorker>(it.applicationContext, widgetIds)
                }
            else -> super.onReceive(context, intent)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        appWidgetIds.forEach { updateAppWidget(context, appWidgetManager, it, null)}
    }

    override fun onEnabled(context: Context) {
        startPeriodicWidgetUpdates<MyHiltWidgetWorker>(context.applicationContext, getWidgetIds(context))
    }

    override fun onDisabled(context: Context) {
        with(WorkManager.getInstance(context)) {
            cancelUniqueWork(WIDGET_UPDATE_WORK)
            cancelUniqueWork(WIDGET_PERIODIC_WORK)
        }
    }
}

internal fun getWidgetIds(context: Context): IntArray? = AppWidgetManager.getInstance(context)
    .getAppWidgetIds(ComponentName(context, MyWidget::class.java))
