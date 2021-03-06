package me.test.jcdecaux.presentation.model

import android.os.Parcel
import android.os.Parcelable

data class StationItem(
    val number: Int,
    val contract_name: String?,
    val name: String?,
    val address: String?,
    val position: PositionItem?,
    val banking: Boolean,
    val bonus: Boolean,
    val bike_stands: Int,
    val available_bike_stands: Int,
    val available_bikes: Int,
    val status: String?,
    val last_update: Double
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readParcelable<PositionItem>(PositionItem::class.java.classLoader),
        1 == source.readInt(),
        1 == source.readInt(),
        source.readInt(),
        source.readInt(),
        source.readInt(),
        source.readString(),
        source.readDouble()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(number)
        writeString(contract_name)
        writeString(name)
        writeString(address)
        writeParcelable(position, 0)
        writeInt((if (banking) 1 else 0))
        writeInt((if (bonus) 1 else 0))
        writeInt(bike_stands)
        writeInt(available_bike_stands)
        writeInt(available_bikes)
        writeString(status)
        writeDouble(last_update)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<StationItem> =
            object : Parcelable.Creator<StationItem> {
                override fun createFromParcel(source: Parcel): StationItem = StationItem(source)
                override fun newArray(size: Int): Array<StationItem?> = arrayOfNulls(size)
            }
    }
}
