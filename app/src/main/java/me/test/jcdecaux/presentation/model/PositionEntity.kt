package me.test.jcdecaux.presentation.model

import android.os.Parcel
import android.os.Parcelable

data class PositionEntity(

    val lat: Double,
    val lng: Double
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readDouble(),
        source.readDouble()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeDouble(lat)
        writeDouble(lng)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PositionEntity> =
            object : Parcelable.Creator<PositionEntity> {
                override fun createFromParcel(source: Parcel): PositionEntity =
                    PositionEntity(source)

                override fun newArray(size: Int): Array<PositionEntity?> = arrayOfNulls(size)
            }
    }
}