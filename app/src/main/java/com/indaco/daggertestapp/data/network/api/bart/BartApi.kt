package com.indaco.daggertestapp.data.network.api.bart

import com.indaco.daggertestapp.data.network.result.bart.GetStationsResult
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BartApi @Inject constructor(private val bartService: BartService) {
    fun getStations(): Response<GetStationsResult> = bartService.getStations().execute()
}