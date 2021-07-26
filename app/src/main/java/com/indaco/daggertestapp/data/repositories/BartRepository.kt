package com.indaco.daggertestapp.data.repositories

import com.indaco.daggertestapp.data.model.bart.BartStation
import com.indaco.daggertestapp.data.network.api.bart.BartApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BartRepository @Inject constructor(
    private val bartApi: BartApi
) {
    suspend fun getBartStations(): Flow<Response<List<BartStation>?>> {
        return flowOf(bartApi.getStations()).map { response ->
            if (response.isSuccessful) {
                val stations = response.body()?.getStations()
                Response.success(stations)
            } else
                Response.error(response.errorBody()!!, response.raw())
        }
    }
}