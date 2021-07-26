package com.indaco.daggertestapp.data.model.bart

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BartStation(
    val name: String,
    @PrimaryKey val abbr: String,
    @SerializedName("gtfs_latitude") val latitude: Double,
    @SerializedName("gtfs_longitude") val longitude: Double,
    var favorite: Boolean = false
): Parcelable {

    override fun equals(other: Any?) =
            when (other) {
                null -> false
                !is BartStation -> false
                else -> other.abbr == abbr
            }

    override fun hashCode(): Int {
        return abbr.hashCode()
    }
}