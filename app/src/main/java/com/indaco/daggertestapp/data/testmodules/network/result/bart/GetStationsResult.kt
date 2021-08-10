package com.indaco.daggertestapp.data.testmodules.network.result.bart

import com.indaco.daggertestapp.data.model.bart.BartStation

class GetStationsResult(val root: Root) {
    inner class Root(val stations: Station)
    inner class Station(val station: List<BartStation>)

    fun getStations(): List<BartStation> = root.stations.station
}