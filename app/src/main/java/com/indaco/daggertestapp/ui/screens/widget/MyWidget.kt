package com.indaco.daggertestapp.ui.screens.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.work.WorkManager
import com.indaco.daggertestapp.R
import com.indaco.daggertestapp.data.worker.widget.MyWidgetWorker
import com.indaco.daggertestapp.data.worker.widget.MyWidgetWorker.Companion.WIDGET_PERIODIC_WORK
import com.indaco.daggertestapp.data.worker.widget.MyWidgetWorker.Companion.WIDGET_UPDATE_WORK
import com.indaco.daggertestapp.ui.screens.widget.MyWidgetUpdater.updateAppWidget

const val ACTION_UPDATE_MY_WIDGET = "ACTION_UPDATE_MY_WIDGET"
const val EXTRA_WIDGET_ID = "EXTRA_WIDGET_ID"
const val EXTRA_WIDGET_IDS = "EXTRA_WIDGET_IDS"

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

                    MyWidgetWorker.updateWidgetDataOnce(it.applicationContext, widgetIds)
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
        MyWidgetWorker.startPeriodicWidgetUpdates(context.applicationContext, getWidgetIds(context))
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
