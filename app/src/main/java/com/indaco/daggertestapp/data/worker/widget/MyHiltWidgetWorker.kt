package com.indaco.daggertestapp.data.worker.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.indaco.daggertestapp.data.repositories.WidgetRepository
import com.indaco.daggertestapp.ui.screens.widget.EXTRA_WIDGET_IDS
import com.indaco.daggertestapp.ui.screens.widget.MyWidgetData
import com.indaco.daggertestapp.ui.screens.widget.MyWidgetUpdater
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class MyHiltWidgetWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val widgetRepository: WidgetRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val appWidgetIds = inputData.getIntArray(EXTRA_WIDGET_IDS)

        val data = widgetRepository.myWidgetData
        var number = data.data.toIntOrNull() ?: 0
        number++
        widgetRepository.myWidgetData = MyWidgetData(number.toString())

        Log.d("TAG","number: $number")

        val widgetData = MyWidgetData(number.toString())
        withContext(Dispatchers.Main) {

            val appWidgetManager = AppWidgetManager.getInstance(applicationContext)
            appWidgetIds?.forEach {
                MyWidgetUpdater.updateAppWidget(applicationContext, appWidgetManager, it, widgetData)
            }
        }

        return Result.success()
    }

    @AssistedFactory
    interface MyHiltWidgetFactory {
        fun create(appContext: Context, params: WorkerParameters): MyHiltWidgetWorker
    }
}