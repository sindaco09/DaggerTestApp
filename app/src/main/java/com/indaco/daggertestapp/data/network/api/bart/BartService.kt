package com.indaco.daggertestapp.data.network.api.bart

import com.indaco.daggertestapp.data.network.result.bart.GetStationsResult
import retrofit2.Call
import retrofit2.http.GET

interface BartService {

    companion object {
        const val BASE_URL = "https://api.bart.gov/api/"
        private const val PUBLIC_BART_KEY = "MW9S-E7SL-26DU-VV8V"
    }

    @GET(BASE_URL + "stn.aspx?cmd=stns&key=${PUBLIC_BART_KEY}&json=y")
    fun getStations(): Call<GetStationsResult>

}