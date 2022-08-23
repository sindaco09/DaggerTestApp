package com.indaco.daggertestapp.ui.screens.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.indaco.daggertestapp.R

object MyWidgetUpdater {
    internal fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        widgetData: MyWidgetData? = MyWidgetData("zero")
    ) {
        appWidgetManager.updateAppWidget(
            appWidgetId,
            RemoteViews(context.packageName, R.layout.my_widget).apply {
                setTextViewText(R.id.appwidget_text, widgetData?.data)
                setOnClickPendingIntent(R.id.appwidget_button, buildIntent(context, appWidgetId))
            }
        )
    }

    private fun buildIntent(context: Context, appWidgetId: Int): PendingIntent =
        PendingIntent.getBroadcast(context,
            1,
            Intent(context, MyWidget::class.java)
                .putExtra(EXTRA_WIDGET_ID, appWidgetId)
                .setAction(ACTION_UPDATE_MY_WIDGET),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
}